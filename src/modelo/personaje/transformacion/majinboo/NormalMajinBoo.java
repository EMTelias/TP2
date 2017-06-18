package modelo.personaje.transformacion.majinboo;

import modelo.personaje.transformacion.Transformacion;


public class NormalMajinBoo extends Transformacion{
    protected static final int poderDePelea = 30;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new BooMaloMajinBoo();
    protected static final int kiNecesarioTransformar = 20;

    public NormalMajinBoo() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }
}
