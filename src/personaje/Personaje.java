package personaje;

import Consumibles.Consumible;
import Consumibles.EsferaDelDragon;
import acciones.Ataque;
import acciones.AtaqueEspecialHandler;
import equipo.Equipo;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.transformacion.KiInsuficienteException;
import excepciones.transformacion.NoHayProximaTransformacionException;
import excepciones.transformacion.NoPuedeTransformarException;
import tablero.Camino;
import tablero.Casillero;
import excepciones.tablero.CasilleroOcupadoException;
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

	/*public boolean tieneElConsumible(Consumible unConsumible){
		return false;
	}*/

	public int distanciaA(Personaje unPersonaje){
		return (this.casillero.distanciaHasta(unPersonaje.casillero));
	}


	public void atacarA(Personaje otroPersonaje) throws NoPuedeAtacarAEsaDistanciaException, NoPuedeAtacarMismoEquipoException {
		Ataque ataque = new Ataque(this, otroPersonaje, 1);
		ataque.execute();
	}

	public void ataqueEspecialA(Personaje otroPersonaje) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
		AtaqueEspecialHandler handler = this.ataqueEspecialMap.get(otroPersonaje.getClass());
		handler.ataqueEspecialA(otroPersonaje);
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
		Casillero casilleroDestino = transformacion.mover(this,camino);
		casillero.vaciar();
		casillero = casilleroDestino;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}
}