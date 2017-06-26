package modelo.personaje.transformacion.goku;

import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.personaje.Personaje;
import modelo.personaje.transformacion.Transformacion;


public class SuperSayajinGoku extends Transformacion {

    protected static final int poderDePelea = 60;
    protected static final int distanciaAtaque = 4;
    protected static final int velocidad = 5;

    public SuperSayajinGoku() {
        super(poderDePelea, distanciaAtaque, velocidad, null, 0);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) {
        throw new NoHayProximaTransformacionException();
    }
}
