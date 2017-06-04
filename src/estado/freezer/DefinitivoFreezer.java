package estado.freezer;

import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;

public class DefinitivoFreezer implements EstadoFreezer {

    final int VELOCIDAD = 6;
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
