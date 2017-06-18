package personaje;

import acciones.AtaqueEspecialHandler;
import equipos.Equipo;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.transformacion.KiInsuficienteException;
import excepciones.transformacion.NoHayProximaTransformacionException;
import excepciones.transformacion.NoPuedeTransformarException;
import tablero.Camino;
import tablero.Casillero;
import transformacion.Chocolate;
import transformacion.Transformacion;

import java.util.HashMap;

public abstract class Personaje {

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
		this.ki+=aumento;
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
		this.vida += cantidad;
	}

	public void reducirVida(int cantidad){
		this.vida-=cantidad;
	}

	public int getVida() {
		return this.vida;
	}

	public int distanciaA(Personaje unPersonaje){
		return (this.casillero.distanciaHasta(unPersonaje.casillero));
	}


	public void atacarA(Personaje otroPersonaje) throws NoPuedeAtacarAEsaDistanciaException, NoPuedeAtacarMismoEquipoException {
		transformacion.atacarA(this,otroPersonaje);
		/*
		Ataque ataque = new Ataque(this, otroPersonaje, 1);
		ataque.execute();
		*/
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
}