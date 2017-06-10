package transformacion.cell;


import excepciones.transformacion.KiInsuficienteException;
import excepciones.transformacion.NoHayProximaTransformacionException;
import excepciones.transformacion.NoPuedeTransformarException;
import personaje.Cell;
import personaje.Personaje;
import transformacion.Transformacion;

public class SemiPerfectoCell extends Transformacion{
    protected static final int poderDePelea = 15;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new PerfectoCell();
    protected static final int kiNecesarioTransformar = 0; //necesita absorber, implementar luego
    protected static final int cantidadAbsorberTransformar = 8;

    public SemiPerfectoCell() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) throws NoPuedeTransformarException {
        if (!cumpleRequisitoTransformacion( (Cell) unPersonaje )) {
            throw new NoPuedeTransformarException();
        }
        return proximaTransformacion;
    }

    private boolean cumpleRequisitoTransformacion(Cell unCell) {
        return unCell.getAbsorber() >= cantidadAbsorberTransformar;
    }
}
