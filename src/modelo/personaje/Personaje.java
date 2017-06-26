package modelo.personaje;

import modelo.acciones.AtaqueEspecialHandler;
import modelo.personaje.equipos.Equipo;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.excepciones.transformacion.NoPuedeTransformarException;
import modelo.tablero.Camino;
import modelo.tablero.Casillero;
import modelo.personaje.transformacion.Chocolate;
import modelo.personaje.transformacion.Transformacion;

import java.util.HashMap;

import modelo.Consumibles.Consumible;
import modelo.Consumibles.Efecto;

public abstract class Personaje {

	protected int VIDA_MAX;
	protected final int AUMENTO_KI = 5;
	protected Casillero casillero;
	protected int ki;
	protected int vida;
	protected Transformacion transformacion;
	protected HashMap<Class, AtaqueEspecialHandler> ataqueEspecialMap;
	private Equipo equipo;

	protected abstract void initAtaqueEspecialMap();
	
	public Personaje(){
		ki = 0;
		this.initAtaqueEspecialMap();
	}

	public void unirse(Equipo unEquipo) {
		unEquipo.agregarPersonaje(this);
		this.equipo = unEquipo;
	}


	public void transformar() throws NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		this.transformacion = transformacion.transformar(this);
	}

	public void aumentarKi(int aumento){
		this.ki+=transformacion.aumentarKi(aumento);
	}

	public void reducirKi (int reduccion) {
		this.ki -= reduccion;
	}

	public boolean tieneSuficienteKi(int kiMinimo) {
		return (this.ki >= kiMinimo);
	}

	public int getKi() {
		return this.ki;
	}

	public void aumentarVida(int cantidad) {
		if ((vida + cantidad)> VIDA_MAX){ 
			vida = VIDA_MAX;
		}else{
			vida += cantidad;
		}
	}

	public void reducirVida(int cantidad){
		this.vida -= cantidad;

		if(vida <= 0){
			this.vida = 0;
			casillero.vaciar();
		}
	}

	public boolean estaMuerto() {
		return vida == 0;
	}

	public int getVida() {
		return this.vida;
	}

	public int distanciaA(Personaje unPersonaje){
		return (this.casillero.distanciaHasta(unPersonaje.casillero));
	}


	public void atacarA(Personaje otroPersonaje) throws NoPuedeAtacarAEsaDistanciaException, NoPuedeAtacarMismoEquipoException {
		// JLS 15.23 - Solo se evalua la derecha si se cumple lo de la izquierda
		if ( (equipo != null) && equipo.esMiembro(otroPersonaje) ) {
			throw new NoPuedeAtacarMismoEquipoException();
		}
		transformacion.atacarA(this,otroPersonaje);
	}

	public AtaqueEspecialHandler getAtaqueEspecialHandlerContra(Personaje unPersonaje){
		return this.ataqueEspecialMap.get(unPersonaje.getClass());
	}

	public void ataqueEspecialA(Personaje otroPersonaje) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
		transformacion.ataqueEspecialA(this,otroPersonaje);
		/*
		AtaqueEspecialHandler handler = this.ataqueEspecialMap.get(otroPersonaje.getClass());
		handler.ataqueEspecialA(otroPersonaje);
		*/
	}

	public int getVelocidad() {
		return transformacion.getVelocidad();
	}

	public int getDistanciaAtaque() {
		return transformacion.getDistanciaAtaque();
	}

	public int getPoderDePelea() {
		return transformacion.getPoderDePelea();
	}


	public void mover(Camino camino) throws NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException {
		transformacion.mover(this,camino);
	}

	public void convertirseEnChocolate(){
		transformacion = new Chocolate(transformacion);//le paso la original
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void dejarDeSerChocolate(){
		transformacion = transformacion.transformacionOriginal();
	}


	public void sacarDeSuCasillero(){
		casillero.vaciar();
		casillero = null;
	}
	
	public void colocarEnCasillero(Casillero unCasillero) throws CasilleroOcupadoException{
		casillero = unCasillero;
		casillero.colocar(this);
	}

	public Casillero getCasillero() {
		return casillero;
	}

	public void consumir(Consumible unConsumible) {
		unConsumible.aplicarConsumibleA(this);
	}

	public void aplicarEfecto(Efecto unEfecto) {
		transformacion.aplicarEfecto(unEfecto);

	}

	private boolean esChocolate(){
		return (transformacion.esChocolate());
	}

    public void revisarTransformacionChocolate(){
		if(this.esChocolate()){
			transformacion = transformacion.revisarTransformacionChocolate();
		}

	}

	public void pasarTurno() {
		this.aumentarKi(AUMENTO_KI);
		transformacion.pasarTurno();
		
	}

	public void sumarEsferaASuEquipo(){
    	if(this.equipo!=null) {
			equipo.sumarEsfera();
		}
	}
}