package estado.piccolo;

import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;

public class ProtectorPiccolo implements EstadoPiccolo {

    final int distanciaDeAtaque = 6;
    final int poderDePelea = 60;
    final int VELOCIDAD = 4;
    int kiNecesarioParaEvolucionar = 0;

    @Override
    public int getVelocidad() {
        return VELOCIDAD;
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