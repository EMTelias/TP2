package estado.goku;

import estado.Estado;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.NoHayQuienRecorraException;
import tablero.Camino;

public class NormalGoku implements EstadoGoku {

	final int distanciaDeAtaque = 2;
	final int vidaMaxima = 500;
	final int poderDePelea = 20;
	final int VELOCIDAD = 2;
	Estado proximoEstado = new KaioKenGoku();
	final int kiNecesarioParaEvolucionar = 20;
	
	@Override
	public void mover(Camino camino) throws NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException, NoHayQuienRecorraException {
		
		if (camino.distancia() > VELOCIDAD ){
			throw new NoPuedeMoverAEsaDistanciaException();
		}
		
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
