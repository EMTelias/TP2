package estado.freezer;

import estado.Estado;

public class SegundaFormaFreezer implements EstadoFreezer {

    final int poderDePelea = 40;
    final int VELOCIDAD = 4;
    Estado proximoEstado = new DefinitivoFreezer();
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


}