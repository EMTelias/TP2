package modelo.personaje.transformacion.gohan;

import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.personaje.Personaje;
import modelo.personaje.transformacion.Transformacion;


public class SuperSayajin2Gohan extends Transformacion{
    protected static final int poderDePelea = 100;
    protected static final int distanciaAtaque = 4;
    protected static final int velocidad = 3;

    public SuperSayajin2Gohan() {
        super(poderDePelea, distanciaAtaque, velocidad, null, 0);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) throws NoHayProximaTransformacionException {
        throw new NoHayProximaTransformacionException();
    }
}
