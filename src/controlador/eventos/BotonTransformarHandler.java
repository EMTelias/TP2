package controlador.eventos;

import controlador.SeleccionarHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.excepciones.transformacion.NoPuedeTransformarException;
import modelo.partida.Partida;
import modelo.tablero.Posicion;
import vista.VistaTablero;

public class BotonTransformarHandler implements EventHandler<ActionEvent> {

    private final Partida partida;
    private final VistaTablero vistaTablero;
    private final SeleccionarHandler seleccionarHandler;

    public BotonTransformarHandler(VistaTablero vistaTablero, Partida partida, SeleccionarHandler seleccionarHandler) {
        this.partida = partida;
        this.vistaTablero = vistaTablero;
        this.seleccionarHandler = seleccionarHandler;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Posicion posPersonaje = seleccionarHandler.getPosicionPersonajeSeleccionado();
        try {
            partida.transformarPersonaje(posPersonaje);
        } catch (NoPuedeTransformarException e) {
            e.printStackTrace();
        } catch (KiInsuficienteException e) {
            e.printStackTrace();
        } catch (NoHayProximaTransformacionException e) {
            e.printStackTrace();
        }
        seleccionarHandler.limpiar();
        vistaTablero.actualizarVista();
    }
}
