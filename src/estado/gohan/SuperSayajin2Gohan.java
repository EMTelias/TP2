package estado.gohan;

import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;

public class SuperSayajin2Gohan implements EstadoGohan {

    final int VELOCIDAD = 3;
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
