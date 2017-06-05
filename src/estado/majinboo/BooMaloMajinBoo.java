package estado.majinboo;

import estado.Estado;

public class BooMaloMajinBoo implements EstadoMajinBoo {

    final int distanciaDeAtaque = 2;
    final int poderDePelea = 50;
    final int VELOCIDAD = 3;
    Estado proximoEstado = new BooOriginalMajinBoo();
    final int kiNecesarioParaEvolucionar = 50;

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

    @Override
    public int getDistanciaDeAtaque() {return distanciaDeAtaque;}
}
