package estado.piccolo;

import estado.Estado;

public class NormalPiccolo implements EstadoPiccolo {

    final int VELOCIDAD = 2;
    Estado proximoEstado = new FortalecidoPiccolo();
    final int kiNecesarioParaEvolucionar = 20;

    @Override
    public int getVelocidad() {
        return VELOCIDAD;
    }

    @Override
    public Estado getProximoEstado() { return proximoEstado; }

    @Override
    public int getKiNecesarioParaTransformar() {return kiNecesarioParaEvolucionar;}
}