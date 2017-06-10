package transformacion.cell;

import excepciones.transformacion.KiInsuficienteException;
import excepciones.transformacion.NoHayProximaTransformacionException;
import excepciones.transformacion.NoPuedeTransformarException;
import personaje.Cell;
import personaje.Personaje;
import transformacion.Transformacion;


public class NormalCell extends Transformacion{
    protected static final int poderDePelea = 20;
    protected static final int distanciaAtaque = 3;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new SemiPerfectoCell();
    protected static final int kiNecesarioTransformar = 0; //necesita absorber, implementar luego
    protected static final int cantidadAbsorberTransformar = 4;

    public NormalCell() {
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
