package transformacion.goku;

import transformacion.Transformacion;


public class NormalGoku extends Transformacion {

    protected static final int poderDePelea = 20;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new KaioKenGoku();
    protected static final int kiNecesarioTransformar = 20;

    public NormalGoku() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }


}
