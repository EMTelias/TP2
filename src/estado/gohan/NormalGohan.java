package estado.gohan;

import estado.Estado;

public class NormalGohan implements EstadoGohan {

    final int poderDePelea = 15;
    final int VELOCIDAD = 2;
    Estado proximoEstado = new SuperSayajin1Gohan();
    final int kiNecesarioParaEvolucionar = 10;

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