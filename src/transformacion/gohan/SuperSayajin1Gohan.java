package transformacion.gohan;


import transformacion.Transformacion;

public class SuperSayajin1Gohan extends Transformacion{
    protected static final int poderDePelea = 30;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new SuperSayajin2Gohan();
    protected static final int kiNecesarioTransformar = 10;

    public SuperSayajin1Gohan() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }
}
