package controlador;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.excepciones.transformacion.NoPuedeTransformarException;
import modelo.partida.Partida;
import modelo.tablero.Posicion;

public class TransformarHandler implements EventHandler<MouseEvent>{

    private final Posicion posPersonaje;
    private final Partida partida;

    public TransformarHandler(Partida partida, Posicion posPersonaje) {
        this.partida = partida;
        this.posPersonaje = posPersonaje;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            partida.transformarPersonaje(posPersonaje);
        } catch (NoPuedeTransformarException e) {
            e.printStackTrace();
        } catch (KiInsuficienteException e) {
            e.printStackTrace();
        } catch (NoHayProximaTransformacionException e) {
            e.printStackTrace();
        }
    }
}
