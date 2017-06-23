package controlador.eventos;

import controlador.SeleccionarHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
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
    private Label consola;
    
    public BotonMoverHandler(VistaTablero vistaTablero, Partida partida, SeleccionarHandler seleccionarHandler, Label unaConsola) {
        this.vistaTablero = vistaTablero;
        this.partida = partida;
        this.seleccionarHandler = seleccionarHandler;
        this.consola = unaConsola;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        consola.setText("");
    	Posicion posPersonaje = seleccionarHandler.getPosicionPersonajeSeleccionado();
        try {
            Camino camino = seleccionarHandler.getCaminoSeleccionado();
            partida.moverEnCamino(posPersonaje, camino);
        } catch (CaminoInvalidoException e) {
            e.printStackTrace();
        } catch (NoPuedeMoverAEsaDistanciaException e) {
            consola.setText("No se puede mover a esa distancia." );;
        } catch (NoPuedeMoverCaminoObstruidoException e) {
            consola.setText("No se puede mover, el camino se encuentra obstruido");
        }
        seleccionarHandler.limpiar();
        vistaTablero.actualizarVista();
    }
}
