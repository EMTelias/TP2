package modelo.personaje.transformacion.majinboo;

import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.personaje.Personaje;
import modelo.personaje.transformacion.Transformacion;


public class BooOriginalMajinBoo extends Transformacion {
    protected static final int poderDePelea = 60;
    protected static final int distanciaAtaque = 3;
    protected static final int velocidad = 4;

    public BooOriginalMajinBoo() {
        super(poderDePelea, distanciaAtaque, velocidad, null, 0);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) throws NoHayProximaTransformacionException {
        throw new NoHayProximaTransformacionException();
    }
}
