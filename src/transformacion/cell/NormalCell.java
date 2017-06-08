package transformacion.cell;

import transformacion.Transformacion;


public class NormalCell extends Transformacion{
    protected static final int poderDePelea = 20;
    protected static final int distanciaAtaque = 3;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new SemiPerfectoCell();
    protected static final int kiNecesarioTransformar = 0; //necesita absorber, implementar luego

    public NormalCell() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }
}
