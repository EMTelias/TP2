package controlador.eventos;

import controlador.CaminoController;
import controlador.PersonajeController;
import controlador.SeleccionarHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.excepciones.transformacion.NoPuedeTransformarException;
import modelo.partida.Partida;
import modelo.personaje.Personaje;
import modelo.tablero.Posicion;
import vista.VistaInfo;
import vista.VistaTablero;

public class BotonTransformarHandler implements EventHandler<ActionEvent> {

    private final Partida partida;
    private final VistaTablero vistaTablero;
    private final PersonajeController personajeController;
    private final CaminoController caminoController;
    private final VistaInfo info;
    private Label consola;

    public BotonTransformarHandler(VistaTablero vistaTablero, Partida partida, CaminoController caminoController, PersonajeController personajeController, VistaInfo info, Label unaConsola) {
        this.partida = partida;
        this.vistaTablero = vistaTablero;
        this.caminoController = caminoController;
        this.personajeController = personajeController;
        this.info = info;
        this.consola = unaConsola;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        /*Posicion posPersonaje = seleccionarHandler.getPosicionPersonajeSeleccionado();
        try {
            partida.transformarPersonaje(posPersonaje);
        } catch (NoPuedeTransformarException e) {
            e.printStackTrace();
        } catch (KiInsuficienteException e) {
            e.printStackTrace();
        } catch (NoHayProximaTransformacionException e) {
            e.printStackTrace();
        }
        seleccionarHandler.limpiar();*/
        Personaje unPersonaje = personajeController.obtenerPersonaje();
        try {
            partida.transformar(unPersonaje);
        } catch (NoPuedeTransformarException e) {
            consola.setText("No se cumplen condiciones de transformacion!");
        } catch (KiInsuficienteException e) {
            consola.setText(unPersonaje.getClass().getSimpleName() + " no posee ki suficiente!");
        } catch (NoHayProximaTransformacionException e) {
            consola.setText(unPersonaje.getClass().getSimpleName() + " esta en su ultima transformacion!");
        }
        personajeController.limpiar();
        caminoController.limpiar();
        info.setDefault();
        vistaTablero.actualizarVista();
    }
}
