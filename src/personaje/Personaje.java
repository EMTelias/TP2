package personaje;

import Consumibles.Consumible;
import Consumibles.EsferaDelDragon;
import acciones.Ataque;
import acciones.AtaqueEspecialHandler;
import estado.goku.EstadoGoku;
import estado.Estado;
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
import excepciones.tablero.NoHayQuienRecorraException;
import transformacion.Transformacion;

import java.util.HashMap;

public abstract class Personaje {

	protected Casillero casillero;
	protected Estado estado;
	protected int ki;
	protected int vida;
	protected Transformacion transformacion;
	protected HashMap<Class, AtaqueEspecialHandler> ataqueEspecialMap;

	protected abstract void initAtaqueEspecialMap();
	
	public Personaje(){
		ki = 0;
		this.initAtaqueEspecialMap();
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

	public void setKi(int nuevoKi){
		this.ki = nuevoKi;
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

	public boolean tieneElConsumible(Consumible unConsumible){
		return false;
	}

	public int distanciaA(Personaje unPersonaje){
		return (this.casillero.distanciaHasta(unPersonaje.casillero));
	}

	/*public void ataqueBasicoA(Personaje objetivo)throws NoPuedeAtacarAEsaDistanciaException{
		//Segun tenga la "Esfera del Dragon (+25%dmg)" y/o ataque a un enemigo de mayor poder (-20%dmg)
		//Ej: si tengo la esfera y ataco a alguien mas debil que yo, tendria un aumento del 25% => danioFinal=(poderDeAtaque * 1,25)
		if (this.distanciaA(objetivo) > estado.getDistanciaDeAtaque()) {
			throw new NoPuedeAtacarAEsaDistanciaException();
		}
		float multiplicadorDeDanio = 1;
		if (this.tieneElConsumible( new EsferaDelDragon() )) {
			multiplicadorDeDanio += 0.25;
		}
		if (objetivo.estado.getPoderDePelea() > this.estado.getPoderDePelea()) {
			multiplicadorDeDanio -= 0.2;
		}
		float danioFinal = (estado.getPoderDePelea() * multiplicadorDeDanio);
		objetivo.reducirVida((int)danioFinal);
	}*/

	public void atacarA(Personaje otroPersonaje) throws NoPuedeAtacarAEsaDistanciaException {
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


	public void mover(Camino camino) throws NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException, NoHayQuienRecorraException {
		
		transformacion.mover(camino);
	}


}
