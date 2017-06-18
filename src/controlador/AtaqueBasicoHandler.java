package controlador;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.partida.Partida;
import modelo.tablero.Posicion;


public class AtaqueBasicoHandler implements EventHandler<MouseEvent> {

    private final Partida partida;
    private final Posicion posAtacante;
    private final Posicion posAtacado;

    public AtaqueBasicoHandler(Partida partida, Posicion posAtacante, Posicion posAtacado) {
        this.partida = partida;
        this.posAtacante = posAtacante;
        this.posAtacado = posAtacado;

    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            partida.atacarEnPosicion(posAtacante, posAtacado);
        } catch (NoPuedeAtacarMismoEquipoException e) {
            e.printStackTrace();
        } catch (NoPuedeAtacarAEsaDistanciaException e) {
            e.printStackTrace();
        }
    }
}
