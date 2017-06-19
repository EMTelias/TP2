package controlador.eventos;

import controlador.SeleccionarHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.partida.Partida;
import modelo.tablero.Camino;
import modelo.tablero.Posicion;
import vista.VistaTablero;

public class BotonMoverHandler implements EventHandler<ActionEvent> {

    private final VistaTablero vistaTablero;
    private final Partida partida;
    private final SeleccionarHandler seleccionarHandler;

    public BotonMoverHandler(VistaTablero vistaTablero, Partida partida, SeleccionarHandler seleccionarHandler) {
        this.vistaTablero = vistaTablero;
        this.partida = partida;
        this.seleccionarHandler = seleccionarHandler;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Posicion posPersonaje = seleccionarHandler.getPosicionPersonajeSeleccionado();
        try {
            Camino camino = seleccionarHandler.getCaminoSeleccionado();
            partida.moverEnCamino(posPersonaje, camino);
        } catch (CaminoInvalidoException e) {
            e.printStackTrace();
        } catch (NoPuedeMoverAEsaDistanciaException e) {
            e.printStackTrace();
        } catch (NoPuedeMoverCaminoObstruidoException e) {
            e.printStackTrace();
        }
        seleccionarHandler.limpiar();
        vistaTablero.actualizarVista();
    }
}
