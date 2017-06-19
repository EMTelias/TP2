package controlador.eventos;

import controlador.SeleccionarHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.partida.Partida;
import modelo.tablero.Posicion;
import vista.VistaTablero;

public class BotonAtaqueEspecialHandler implements EventHandler<ActionEvent> {

    private final Partida partida;
    private final VistaTablero vistaTablero;
    private final SeleccionarHandler seleccionarHandler;

    public BotonAtaqueEspecialHandler(VistaTablero vistaTablero, Partida partida, SeleccionarHandler seleccionarHandler) {
        this.partida = partida;
        this.vistaTablero = vistaTablero;
        this.seleccionarHandler = seleccionarHandler;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Posicion posAtacante = seleccionarHandler.getPosicionPersonajeSeleccionado();
        Posicion posAtacado = seleccionarHandler.getPosicionPersonajeSeleccionado();
        try {
            partida.ataqueEspecialEnPosicion(posAtacante, posAtacado);
        } catch (NoPuedeAtacarMismoEquipoException e) {
            e.printStackTrace();
        } catch (NoPuedeAtacarAEsaDistanciaException e) {
            e.printStackTrace();
        } catch (KiInsuficienteException e) {
            e.printStackTrace();
        }
        seleccionarHandler.limpiar();
        vistaTablero.actualizarVista();
    }
}
