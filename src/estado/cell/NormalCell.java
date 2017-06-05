package estado.cell;

import estado.Estado;

public class NormalCell implements EstadoCell {

    final int poderDePelea = 20;
    final int VELOCIDAD = 2;
    Estado proximoEstado = new SemiPerfectoCell();
    final int kiNecesarioParaEvolucionar = 0;

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