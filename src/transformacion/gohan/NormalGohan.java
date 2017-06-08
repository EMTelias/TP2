package transformacion.gohan;


import transformacion.Transformacion;

public class NormalGohan extends Transformacion {
    protected static final int poderDePelea = 15;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new SuperSayajin1Gohan();
    protected static final int kiNecesarioTransformar = 10;

    public NormalGohan() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }
}
