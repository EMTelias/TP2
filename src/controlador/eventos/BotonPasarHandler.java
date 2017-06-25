package controlador.eventos;

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
    private Label consola;

    public BotonPasarHandler(Partida partida, VistaTablero vistaTablero, VistaInfo info, Label unaConsola) {
        this.partida = partida;
        this.vistaTablero = vistaTablero;
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
        vistaTablero.actualizarVista();
    }
}