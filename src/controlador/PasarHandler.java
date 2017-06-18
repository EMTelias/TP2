package controlador;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import modelo.partida.Partida;

public class PasarHandler implements EventHandler<MouseEvent>{

    private final Partida partida;

    public PasarHandler(Partida partida) {
        this.partida = partida;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        partida.pasar();
    }
}
