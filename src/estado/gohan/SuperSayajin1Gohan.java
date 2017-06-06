package estado.gohan;

import estado.Estado;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import tablero.Camino;

public class SuperSayajin1Gohan implements EstadoGohan {

    final int distanciaDeAtaque = 2;
    final int poderDePelea = 30;
    final int VELOCIDAD = 2;
    Estado proximoEstado = new SuperSayajin2Gohan();
    final int kiNecesarioParaEvolucionar = 30; //necesita algo mas tambien, ver luego

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