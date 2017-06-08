package transformacion.cell;


import excepciones.transformacion.NoHayProximaTransformacionException;
import personaje.Personaje;
import transformacion.Transformacion;

public class PerfectoCell extends Transformacion{
    protected static final int poderDePelea = 15;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 2;

    public PerfectoCell() {
        super(poderDePelea, distanciaAtaque, velocidad, null, 0);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) throws NoHayProximaTransformacionException {
        throw new NoHayProximaTransformacionException();
    }
}
