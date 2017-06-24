package controlador.eventos;

import controlador.CaminoController;
import controlador.PersonajeController;
import controlador.SeleccionarHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.partida.Partida;
import modelo.personaje.Personaje;
import modelo.tablero.Posicion;
import vista.VistaTablero;

public class BotonAtaqueBasicoHandler implements EventHandler<ActionEvent> {

    private final Partida partida;
    private final VistaTablero vistaTablero;
    //private final SeleccionarHandler seleccionarHandler;
    private final PersonajeController personajeController;
    private final CaminoController caminoController;
    private Label consola;

    public BotonAtaqueBasicoHandler(VistaTablero vistaTablero, Partida partida, CaminoController caminoController, PersonajeController personajeController, Label unaConsola) {
        this.partida = partida;
        this.vistaTablero = vistaTablero;
        //this.seleccionarHandler = seleccionarHandler;
        this.caminoController = caminoController;
        this.personajeController = personajeController;
        this.consola = unaConsola;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
    	/*consola.setText("");
        Posicion posAtacante = seleccionarHandler.getPosicionPersonajeSeleccionado();
        Posicion posAtacado = seleccionarHandler.getPosicionPersonajeSeleccionado();
        try {
            partida.atacarEnPosicion(posAtacante, posAtacado);
        } catch (NoPuedeAtacarMismoEquipoException e) {
        	consola.setText("No puede atacar a miembro del mismo equipo.");
        } catch (NoPuedeAtacarAEsaDistanciaException e) {
        	consola.setText("No puede atacar a esa distancia.");
        }
        seleccionarHandler.limpiar();*/
        Personaje atacante = personajeController.obtenerPersonaje();
        Personaje atacado = personajeController.obtenerPersonaje();
        try {
            partida.ataqueBasico(atacante, atacado);
        } catch (NoPuedeAtacarMismoEquipoException e) {
            consola.setText(atacante.getClass().getSimpleName() + " no puede atacar a su amigo " + atacado.getClass().getSimpleName() + "!");
        } catch (NoPuedeAtacarAEsaDistanciaException e) {
            consola.setText(atacante.getClass().getSimpleName() + " no puede atacar a esa distancia.");
        }
        personajeController.limpiar();
        caminoController.limpiar();
        vistaTablero.actualizarVista();
    }
}
