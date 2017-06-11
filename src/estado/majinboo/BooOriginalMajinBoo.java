package estado.majinboo;

import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.NoHayQuienRecorraException;
import tablero.Camino;

public class BooOriginalMajinBoo implements EstadoMajinBoo {

    final int distanciaDeAtaque = 3;
    final int poderDePelea = 60;
    final int VELOCIDAD = 4;
    int kiNecesarioParaEvolucionar = 0;

    @Override
	public void mover(Camino camino) throws NoPuedeMoverCaminoObstruidoException, NoHayQuienRecorraException {
		camino.recorrer();
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
