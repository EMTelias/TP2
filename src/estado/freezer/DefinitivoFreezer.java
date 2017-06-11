package estado.freezer;

import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.NoHayQuienRecorraException;
import tablero.Camino;

public class DefinitivoFreezer implements EstadoFreezer {

    final int distanciaDeAtaque = 3;
    final int poderDePelea = 50;
    final int VELOCIDAD = 6;
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
