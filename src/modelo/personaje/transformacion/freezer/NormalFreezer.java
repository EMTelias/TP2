package modelo.personaje.transformacion.freezer;


import modelo.personaje.transformacion.Transformacion;

public class NormalFreezer extends Transformacion {
    protected static final int poderDePelea = 20;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 4;
    protected static final Transformacion proximaTransformacion = new SegundaFormaFreezer();
    protected static final int kiNecesarioTransformar = 20;

    public NormalFreezer() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }
}
