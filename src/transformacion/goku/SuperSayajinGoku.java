package transformacion.goku;

import excepciones.transformacion.NoHayProximaTransformacionException;
import personaje.Personaje;
import transformacion.Transformacion;


public class SuperSayajinGoku extends Transformacion {

    protected static final int poderDePelea = 60;
    protected static final int distanciaAtaque = 4;
    protected static final int velocidad = 5;

    public SuperSayajinGoku() {
        super(poderDePelea, distanciaAtaque, velocidad, null, 0);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) throws NoHayProximaTransformacionException {
        throw new NoHayProximaTransformacionException();
    }
}
