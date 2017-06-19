package controlador.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.partida.Partida;
import vista.VistaTablero;

public class BotonPasarHandler implements EventHandler<ActionEvent> {

    private final Partida partida;
    private final VistaTablero vistaTablero;

    public BotonPasarHandler(Partida partida, VistaTablero vistaTablero) {
        this.partida = partida;
        this.vistaTablero = vistaTablero;
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
        vistaTablero.actualizarVista();
    }
}