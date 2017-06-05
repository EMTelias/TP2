package estado.piccolo;

import estado.Estado;

public class FortalecidoPiccolo implements EstadoPiccolo {

    final int poderDePelea = 40;
    final int VELOCIDAD = 3;
    Estado proximoEstado = new ProtectorPiccolo();
    int kiNecesarioParaEvolucionar = 0; //no necesita ki, necesita de gohan. Modificar luego

    @Override
    public int getVelocidad() {
        return VELOCIDAD;
    }

    @Override
    public Estado getProximoEstado() { return proximoEstado; }

    @Override
    public int getKiNecesarioParaTransformar() {return kiNecesarioParaEvolucionar;}

    @Override
    public int getPoderDePelea(){return poderDePelea;}

}
