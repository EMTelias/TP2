package estado.piccolo;

import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;

public class ProtectorPiccolo implements EstadoPiccolo {

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
}