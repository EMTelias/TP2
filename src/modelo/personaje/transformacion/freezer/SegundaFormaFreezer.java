package modelo.personaje.transformacion.freezer;

import modelo.personaje.transformacion.Transformacion;


public class SegundaFormaFreezer extends Transformacion{
    protected static final int poderDePelea = 40;
    protected static final int distanciaAtaque = 3;
    protected static final int velocidad = 4;
    protected static final Transformacion proximaTransformacion = new DefinitivoFreezer();
    protected static final int kiNecesarioTransformar = 50;

    public SegundaFormaFreezer() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }
}
