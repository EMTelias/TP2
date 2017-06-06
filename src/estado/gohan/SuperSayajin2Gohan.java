package estado.gohan;

import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import tablero.Camino;

public class SuperSayajin2Gohan implements EstadoGohan {

    final int distanciaDeAtaque = 4;
    final int poderDePelea = 100;
    final int VELOCIDAD = 3;
    int kiNecesarioParaEvolucionar = 0;

    @Override
    public void mover(Camino camino) throws NoPuedeMoverCaminoObstruidoException {
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
