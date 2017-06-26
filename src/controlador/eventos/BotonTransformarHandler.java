package controlador.eventos;

import controlador.CaminoController;
import controlador.PersonajeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.acciones.NoPuedeTransformarseSiendoChocolateException;
import modelo.excepciones.personaje.NoEsSuTurnoException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.excepciones.transformacion.NoPuedeTransformarException;
import modelo.partida.Partida;
import modelo.personaje.Personaje;
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
        Personaje unPersonaje = personajeController.obtenerPersonaje();
        try {
            partida.transformar(unPersonaje);
            consola.setText( unPersonaje.getClass().getSimpleName() + " se ha transformado!");
        } catch (NoPuedeTransformarException e) {
            consola.setText("No se cumplen condiciones de transformacion!");
        } catch (KiInsuficienteException e) {
            consola.setText(unPersonaje.getClass().getSimpleName() + " no posee ki suficiente!");
        } catch (NoHayProximaTransformacionException e) {
            consola.setText(unPersonaje.getClass().getSimpleName() + " esta en su ultima transformacion!");
        } catch (NoPuedeTransformarseSiendoChocolateException e) {
            consola.setText(unPersonaje.getClass().getSimpleName() + " no puede transformar este turno. Es chocolate.");
        } catch (NoEsSuTurnoException e){
            consola.setText("No es el turno de este personaje.");
        }
        personajeController.limpiar();
        caminoController.limpiar();
        info.setStatsDefault();
        vistaTablero.actualizarVista();
    }
}
