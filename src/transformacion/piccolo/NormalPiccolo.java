package transformacion.piccolo;


import transformacion.Transformacion;
import transformacion.gohan.SuperSayajin1Gohan;

public class NormalPiccolo extends Transformacion{
    protected static final int poderDePelea = 20;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new FortalecidoPiccolo();
    protected static final int kiNecesarioTransformar = 20;

    public NormalPiccolo() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }
}
