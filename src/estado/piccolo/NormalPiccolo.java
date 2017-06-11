package estado.piccolo;

import estado.Estado;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.NoHayQuienRecorraException;
import tablero.Camino;

public class NormalPiccolo implements EstadoPiccolo {

    final int distanciaDeAtaque = 2;
    final int poderDePelea = 20;
    final int VELOCIDAD = 2;
    Estado proximoEstado = new FortalecidoPiccolo();
    final int kiNecesarioParaEvolucionar = 20;

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