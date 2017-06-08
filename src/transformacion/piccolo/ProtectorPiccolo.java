package transformacion.piccolo;


import excepciones.transformacion.NoHayProximaTransformacionException;
import personaje.Personaje;
import transformacion.Transformacion;

public class ProtectorPiccolo extends Transformacion{
    protected static final int poderDePelea = 60;
    protected static final int distanciaAtaque = 6;
    protected static final int velocidad = 4;

    public ProtectorPiccolo() {
        super(poderDePelea, distanciaAtaque, velocidad, null, 0);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) throws NoHayProximaTransformacionException {
        throw new NoHayProximaTransformacionException();
    }
}
