package modelo.personaje.transformacion.cell;


import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.personaje.Personaje;
import modelo.personaje.transformacion.Transformacion;

public class PerfectoCell extends Transformacion{
    protected static final int poderDePelea = 80;
    protected static final int distanciaAtaque = 4;
    protected static final int velocidad = 4;

    public PerfectoCell() {
        super(poderDePelea, distanciaAtaque, velocidad, null, 0);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) {
        throw new NoHayProximaTransformacionException();
    }
}
