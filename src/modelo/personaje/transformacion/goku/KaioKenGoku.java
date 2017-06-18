package modelo.personaje.transformacion.goku;


import modelo.personaje.transformacion.Transformacion;

public class KaioKenGoku extends Transformacion{

    protected static final int poderDePelea = 40;
    protected static final int distanciaAtaque = 4;
    protected static final int velocidad = 3;
    protected static final Transformacion proximaTransformacion = new SuperSayajinGoku();
    protected static final int kiNecesarioTransformar = 50;

    public KaioKenGoku() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }

}
