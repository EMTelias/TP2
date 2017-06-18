package controlador;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.partida.Partida;
import modelo.tablero.Camino;
import modelo.tablero.Posicion;



public class MoverHandler implements EventHandler<MouseEvent>{

    private final Partida partida;
    private final Posicion posPersonaje;
    private final Camino camino;

    public MoverHandler(Partida partida, Posicion posPersonaje, Camino camino) {
        this.partida = partida;
        this.posPersonaje = posPersonaje;
        this.camino = camino;

    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            partida.moverEnCamino(posPersonaje, camino);
        } catch (NoPuedeMoverAEsaDistanciaException e) {
            e.printStackTrace();
        } catch (NoPuedeMoverCaminoObstruidoException e) {
            e.printStackTrace();
        } catch (CaminoInvalidoException e) {
            e.printStackTrace();
        }
    }
}
