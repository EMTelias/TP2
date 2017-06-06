package estado.goku;

import estado.Estado;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import tablero.Camino;

public class KaioKenGoku implements EstadoGoku {

	final int distanciaDeAtaque = 4;
	final int vidaMaxima = 500;
	final int poderDePelea = 40;
	final int VELOCIDAD = 3;
	Estado proximoEstado = new SuperSayajinGoku();
	final int kiNecesarioParaEvolucionar = 50;
	
	@Override
	public void mover(Camino camino) throws NoPuedeMoverCaminoObstruidoException {
		camino.recorrer();
	}

	@Override
	public Estado getProximoEstado() { return proximoEstado; }

	@Override
	public int getKiNecesarioParaTransformar() {return kiNecesarioParaEvolucionar;}

	@Override
	public int getPoderDePelea() {return poderDePelea;}

	@Override
	public int getDistanciaDeAtaque() {return distanciaDeAtaque;}

}
