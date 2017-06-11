package estado.freezer;

import estado.Estado;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.NoHayQuienRecorraException;
import tablero.Camino;

public class SegundaFormaFreezer implements EstadoFreezer {

    final int distanciaDeAtaque = 3;
    final int poderDePelea = 40;
    final int VELOCIDAD = 4;
    Estado proximoEstado = new DefinitivoFreezer();
    final int kiNecesarioParaEvolucionar = 50;

    @Override
    public void mover(Camino camino) throws NoPuedeMoverCaminoObstruidoException, NoHayQuienRecorraException {
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