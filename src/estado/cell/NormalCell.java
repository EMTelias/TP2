package estado.cell;

import estado.Estado;

public class NormalCell implements EstadoCell {


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
}