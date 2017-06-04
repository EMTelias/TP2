package estado.majinboo;

import estado.Estado;

public class NormalMajinBoo implements EstadoMajinBoo {

    final int VELOCIDAD = 2;
    Estado proximoEstado = new BooMaloMajinBoo();
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
