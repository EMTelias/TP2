package modelo.personaje.transformacion.cell;


import modelo.excepciones.transformacion.NoPuedeTransformarException;
import modelo.personaje.Cell;
import modelo.personaje.Personaje;
import modelo.personaje.transformacion.Transformacion;

public class SemiPerfectoCell extends Transformacion{
    protected static final int poderDePelea = 40;
    protected static final int distanciaAtaque = 4;
    protected static final int velocidad = 3;
    protected static final Transformacion proximaTransformacion = new PerfectoCell();
    protected static final int kiNecesarioTransformar = 0;
    protected static final int cantidadAbsorberTransformar = 8;

    public SemiPerfectoCell() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje) {
        if (!cumpleRequisitoTransformacion( (Cell) unPersonaje )) {
            throw new NoPuedeTransformarException();
        }
        return proximaTransformacion;
    }

    private boolean cumpleRequisitoTransformacion(Cell unCell) {
        return unCell.getAbsorber() >= cantidadAbsorberTransformar;
    }
}
