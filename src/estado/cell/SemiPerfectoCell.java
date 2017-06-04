package estado.cell;

import estado.Estado;

public class SemiPerfectoCell implements EstadoCell {

    final int VELOCIDAD = 3;
    Estado proximoEstado = new PerfectoCell();
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
