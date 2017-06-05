package estado.freezer;

import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;

public class DefinitivoFreezer implements EstadoFreezer {

    final int poderDePelea = 50;
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

    @Override
    public int getPoderDePelea(){return poderDePelea;}

}
