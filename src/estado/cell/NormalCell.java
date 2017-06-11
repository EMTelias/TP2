package estado.cell;

import estado.Estado;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.NoHayQuienRecorraException;
import tablero.Camino;

public class NormalCell implements EstadoCell {

    final int distanciaDeAtaque = 3;
    final int poderDePelea = 20;
    final int VELOCIDAD = 2;
    Estado proximoEstado = new SemiPerfectoCell();
    final int kiNecesarioParaEvolucionar = 0;

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