package transformacion.piccolo;


import transformacion.Transformacion;

public class FortalecidoPiccolo extends Transformacion{
    protected static final int poderDePelea = 40;
    protected static final int distanciaAtaque = 4;
    protected static final int velocidad = 3;
    protected static final Transformacion proximaTransformacion = new ProtectorPiccolo();
    protected static final int kiNecesarioTransformar = 0; //necesita de Gohan, implementar luego

    public FortalecidoPiccolo() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }
}
