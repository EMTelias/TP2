package estado.freezer;

import estado.Estado;

public class NormalFreezer implements EstadoFreezer {

    final int distanciaDeAtaque = 2;
    final int poderDePelea = 20;
    final int VELOCIDAD = 4;
    Estado proximoEstado = new SegundaFormaFreezer();
    final int kiNecesarioParaEvolucionar = 20;

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