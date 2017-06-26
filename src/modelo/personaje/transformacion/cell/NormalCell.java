package modelo.personaje.transformacion.cell;

import modelo.excepciones.transformacion.NoPuedeTransformarException;
import modelo.personaje.Cell;
import modelo.personaje.Personaje;
import modelo.personaje.transformacion.Transformacion;


public class NormalCell extends Transformacion{
    protected static final int poderDePelea = 20;
    protected static final int distanciaAtaque = 3;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new SemiPerfectoCell();
    protected static final int kiNecesarioTransformar = 0;
    protected static final int cantidadAbsorberTransformar = 4;

    public NormalCell() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje)  {
        if (!cumpleRequisitoTransformacion( (Cell) unPersonaje )) {
            throw new NoPuedeTransformarException();
        }
        return proximaTransformacion;
    }

    private boolean cumpleRequisitoTransformacion(Cell unCell) {
        return unCell.getAbsorber() >= cantidadAbsorberTransformar;
    }
}
