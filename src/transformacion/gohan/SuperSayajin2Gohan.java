package transformacion.gohan;

import excepciones.transformacion.NoHayProximaTransformacionException;
import personaje.Personaje;
import transformacion.Transformacion;


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
