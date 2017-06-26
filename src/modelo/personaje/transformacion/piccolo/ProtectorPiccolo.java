package modelo.personaje.transformacion.piccolo;


import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.personaje.Personaje;
import modelo.personaje.transformacion.Transformacion;

public class ProtectorPiccolo extends Transformacion{
    protected static final int poderDePelea = 60;
    protected static final int distanciaAtaque = 6;
    protected static final int velocidad = 4;

    public ProtectorPiccolo() {
        super(poderDePelea, distanciaAtaque, velocidad, null, 0);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) {
        throw new NoHayProximaTransformacionException();
    }
}
