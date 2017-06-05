package estado.goku;

import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;

public class SuperSayajinGoku implements EstadoGoku {

	final int distanciaDeAtaque = 4;
	int vidaMaxima = 500;
	final int poderDePelea = 60;
	final int VELOCIDAD = 5;
	int kiNecesarioParaEvolucionar = 0;
	
	@Override
	public int getVelocidad() {
		return VELOCIDAD;
	}

	@Override
	public Estado getProximoEstado() throws EstadoNoTieneProximoException { throw new EstadoNoTieneProximoException(); }

	@Override
	public int getKiNecesarioParaTransformar() {return kiNecesarioParaEvolucionar;}

	@Override
	public int getPoderDePelea(){return poderDePelea;}

	@Override
	public int getDistanciaDeAtaque() {return distanciaDeAtaque;}

}
