package controlador.eventos;

import controlador.CaminoController;
import controlador.PersonajeController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.partida.Partida;
import vista.VistaInfo;
import vista.VistaTablero;

public class BotonPasarHandler implements EventHandler<ActionEvent> {

    private final Partida partida;
    private final VistaTablero vistaTablero;
    private final VistaInfo info;
    private final CaminoController caminoController;
    private final PersonajeController personajeController;
    private Label consola;

    public BotonPasarHandler(Partida partida, VistaTablero vistaTablero, CaminoController caminoController, PersonajeController personajeController, VistaInfo info, Label unaConsola) {
        this.partida = partida;
        this.vistaTablero = vistaTablero;
        this.caminoController = caminoController;
        this.personajeController = personajeController;
        this.info = info;
        this.consola = unaConsola;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        /*ArrayList<Casillero> casilleros = new ArrayList<>();
        casilleros.add(partida.getTablero().getCasillero(new Posicion(2,2)));
        try {
            partida.moverEnCamino(new Posicion(1,1), new Camino(casilleros));
        } catch (NoPuedeMoverAEsaDistanciaException e) {
            e.printStackTrace();
        } catch (NoPuedeMoverCaminoObstruidoException e) {
            e.printStackTrace();
        } catch (CaminoInvalidoException e) {
            e.printStackTrace();
        }*/
    	partida.pasar();
        consola.setText("Comienza el turno de los " + partida.turnoActual().getNombre());
        info.setTurno(partida.turnoActual().getNombre());
        info.setDefault();
        // Por si quedan personajes o casilleros seleccionados, al pasar de turno se limpian
        personajeController.limpiar();
        caminoController.limpiar();
        vistaTablero.actualizarVista();
    }
}