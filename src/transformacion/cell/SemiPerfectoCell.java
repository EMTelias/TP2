package transformacion.cell;


import transformacion.Transformacion;

public class SemiPerfectoCell extends Transformacion{
    protected static final int poderDePelea = 15;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new PerfectoCell();
    protected static final int kiNecesarioTransformar = 0; //necesita absorber, implementar luego

    public SemiPerfectoCell() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }
}
