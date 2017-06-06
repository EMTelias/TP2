package estado.gohan;

import estado.Estado;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import tablero.Camino;

public class NormalGohan implements EstadoGohan {

    final int distanciaDeAtaque = 2;
    final int poderDePelea = 15;
    final int VELOCIDAD = 2;
    Estado proximoEstado = new SuperSayajin1Gohan();
    final int kiNecesarioParaEvolucionar = 10;

    @Override
    public void mover(Camino camino) throws NoPuedeMoverCaminoObstruidoException {
    	camino.recorrer();
    }

    @Override
    public Estado getProximoEstado() { return proximoEstado; }

    @Override
    public int getKiNecesarioParaTransformar() {return kiNecesarioParaEvolucionar;}

    @Override
    public int getPoderDePelea(){return poderDePelea;}

    @Override
    public int getDistanciaDeAtaque() {return distanciaDeAtaque;}
}