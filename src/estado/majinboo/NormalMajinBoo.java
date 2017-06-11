package estado.majinboo;

import estado.Estado;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.NoHayQuienRecorraException;
import tablero.Camino;

public class NormalMajinBoo implements EstadoMajinBoo {

    final int distanciaDeAtaque = 2;
    final int poderDePelea = 30;
    final int VELOCIDAD = 2;
    Estado proximoEstado = new BooMaloMajinBoo();
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
