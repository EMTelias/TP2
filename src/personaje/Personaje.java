package personaje;

import Consumibles.Consumible;
import Consumibles.EsferaDelDragon;
import estado.goku.EstadoGoku;
import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeCambiarDeEstadoKiInsuficienteException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import tablero.Camino;
import tablero.Casillero;
import excepciones.tablero.CasilleroOcupadoException;

public abstract class Personaje {

	protected Casillero casillero;
	protected Estado estado;
	protected int ki;
	protected int vida;

	
	public Personaje(){
		ki = 0;
	}


	public void transformar() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException{
		if (ki < estado.getKiNecesarioParaTransformar()) throw new NoPuedeCambiarDeEstadoKiInsuficienteException();
			ki-=estado.getKiNecesarioParaTransformar();
			estado = estado.getProximoEstado();


	}

	public void aumentarKi(int aumento){
		ki+=aumento;
	}

	public void reducirVida(int cantidad){vida-=cantidad;}

	public boolean tieneElConsumible(Consumible unConsumible){
		return false;
	}

	public int distanciaA(Personaje unPersonaje){
		return (this.casillero.distanciaHasta(unPersonaje.casillero));
	}

	public void ataqueBasicoA(Personaje objetivo)throws NoPuedeAtacarAEsaDistanciaException{
		//Segun tenga la "Esfera del Dragon (+25%dmg)" y/o ataque a un enemigo de mayor poder (-20%dmg)
		//Ej: si tengo la esfera y ataco a alguien mas debil que yo, tendria un aumento del 25% => dañoFinal=(poderDeAtaque * 1,25)
		if(this.distanciaA(objetivo)> estado.getDistanciaDeAtaque())throw new NoPuedeAtacarAEsaDistanciaException();
		float multiplicadorDeDanio = 1;
		if(this.tieneElConsumible(new EsferaDelDragon())) {multiplicadorDeDanio+=0.25;}
		if(objetivo.estado.getPoderDePelea() > this.estado.getPoderDePelea()) {multiplicadorDeDanio-=0.2;}
		float danioFinal = (estado.getPoderDePelea() * multiplicadorDeDanio);
		objetivo.reducirVida((int)danioFinal);
	}

	public void mover(Camino camino) throws NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException {
		
		estado.mover(camino);		
	}


}
