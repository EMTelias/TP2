package modelo.personaje.transformacion.majinboo;

import modelo.personaje.transformacion.Transformacion;


public class BooMaloMajinBoo extends Transformacion{
    protected static final int poderDePelea = 50;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 3;
    protected static final Transformacion proximaTransformacion = new BooOriginalMajinBoo();
    protected static final int kiNecesarioTransformar = 50;

    public BooMaloMajinBoo() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }
}
