package personaje;

import Consumibles.Consumible;
import Consumibles.EsferaDelDragon;
import estado.goku.EstadoGoku;
import excepciones.direccion.NoHayDireccionPosibleException;
import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;
import excepciones.personaje.NoPuedeCambiarDeEstadoKiInsuficienteException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
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

	public void ubicarEn(Casillero unCasillero) {
		casillero = unCasillero;
	}


	public void moverA(Casillero casilleroDestino) throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoHayDireccionPosibleException {
		
		if (!casillero.caminoDespejadoHasta(casilleroDestino)) throw new NoPuedeMoverCaminoObstruidoException();
		
		if (casillero.distanciaHasta(casilleroDestino) > estado.getVelocidad() ) throw new NoPuedeMoverAEsaDistanciaException();
		
		casillero.quitar();
		try {
			casilleroDestino.colocar(this);
		} catch (CasilleroOcupadoException e) {
		}

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

	public void ataqueBasicoA(Personaje objetivo){
		//Segun tenga la "Esfera del Dragon (+25%dmg)" y/o ataque a un enemigo de mayor poder (-20%dmg)
		//Ej: si tengo la esfera y ataco a alguien mas debil que yo, tendria un aumento del 25% => dañoFinal=(poderDeAtaque * 1,25)
		float multiplicadorDeDaño = 1;
		if(this.tieneElConsumible(new EsferaDelDragon())) {multiplicadorDeDaño+=0.25;}
		System.out.println(multiplicadorDeDaño);
		if(objetivo.estado.getPoderDePelea() > this.estado.getPoderDePelea()) {multiplicadorDeDaño-=0.2;}
		float dañoFinal = (estado.getPoderDePelea() * multiplicadorDeDaño);
		System.out.println(multiplicadorDeDaño);
		objetivo.reducirVida((int)dañoFinal);
	}
}
