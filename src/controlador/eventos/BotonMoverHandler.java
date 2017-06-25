package controlador.eventos;

import controlador.CaminoController;
import controlador.PersonajeController;
import controlador.SeleccionarHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.acciones.YaMovisteEsteTurnoException;
import modelo.excepciones.personaje.NoEsSuTurnoException;
import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.excepciones.tablero.CasilleroNoContiguoException;
import modelo.excepciones.tablero.HuecoEntreCaminoYPersonajeException;
import modelo.partida.Partida;
import modelo.personaje.Personaje;
import modelo.personaje.equipos.Equipo;
import modelo.tablero.Camino;
import modelo.tablero.Posicion;
import vista.VistaInfo;
import vista.VistaTablero;

public class BotonMoverHandler implements EventHandler<ActionEvent> {

    private final VistaTablero vistaTablero;
    private final Partida partida;
    private final CaminoController caminoController;
    private final PersonajeController personajeController;
    private final VistaInfo info;
    private Label consola;
    
    public BotonMoverHandler(VistaTablero vistaTablero, Partida partida, CaminoController caminoController, PersonajeController personajeController, VistaInfo info, Label unaConsola) {
        this.vistaTablero = vistaTablero;
        this.partida = partida;
        this.caminoController = caminoController;
        this.personajeController = personajeController;
        this.info = info;
        this.consola = unaConsola;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        /*consola.setText("");
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
        seleccionarHandler.limpiar();*/
        Equipo equipoJugando = partida.turnoActual();
        Personaje unPersonaje = personajeController.obtenerPersonaje();
        try {
            Camino camino = caminoController.obtenerCamino();
            partida.mover(unPersonaje, camino);

        } catch (CaminoInvalidoException e) {
            consola.setText("Seleccione un camino valido!");
        } catch (NoPuedeMoverAEsaDistanciaException e) {
            consola.setText( unPersonaje.getClass().getSimpleName() + " no puede mover esa distancia.");
        } catch (NoPuedeMoverCaminoObstruidoException e) {
            consola.setText("Camino obstruido no se puede recorrer");
        } catch (YaMovisteEsteTurnoException e) {
            consola.setText("El equipo de " + unPersonaje.getEquipo().getNombre() + " ya movio este turno!");
        } catch (HuecoEntreCaminoYPersonajeException e){
            consola.setText("El personaje y el camino deben ser contiguos!");
        } catch (NoEsSuTurnoException e){
            consola.setText("No es el turno de este personaje.");
        }

        personajeController.limpiar();
        caminoController.limpiar();

        if (equipoJugando.getNombre().equals(partida.turnoActual().getNombre())) {
            info.setMovimientos("0");
        } else {
            consola.setText("Comienza el turno de los " + partida.turnoActual().getNombre());
            info.setTurno(partida.turnoActual().getNombre());
            info.setAccionesDefault();
        }
        info.setStatsDefault();
        vistaTablero.actualizarVista();
    }
}
