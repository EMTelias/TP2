package controlador;

        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.scene.input.MouseEvent;
        import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
        import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
        import modelo.excepciones.transformacion.KiInsuficienteException;
        import modelo.partida.Partida;
        import modelo.tablero.Posicion;


public class AtaqueEspecialHandler implements EventHandler<MouseEvent> {

    private final Partida partida;
    private final Posicion posAtacante;
    private final Posicion posAtacado;

    public AtaqueEspecialHandler(Partida partida, Posicion posAtacante, Posicion posAtacado) {
        this.partida = partida;
        this.posAtacante = posAtacante;
        this.posAtacado = posAtacado;

    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        try {
            partida.ataqueEspecialEnPosicion(posAtacante, posAtacado);
        } catch (NoPuedeAtacarMismoEquipoException e) {
            e.printStackTrace();
        } catch (NoPuedeAtacarAEsaDistanciaException e) {
            e.printStackTrace();
        } catch (KiInsuficienteException e) {
            e.printStackTrace();
        }

    }
}