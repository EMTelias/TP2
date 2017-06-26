package vista;


import controlador.CaminoController;
import controlador.PersonajeController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modelo.tablero.Casillero;

public class VistaCasillero extends Pane {
    private final Casillero casillero;
    private final CaminoController caminoController;
    private final PersonajeController personajeController;
    private final VistaInfo info;
    private VistaPersonaje personaje;
    private VistaConsumible consumible;
    protected static final int TAM_CASILLERO = 40;
    protected boolean seleccionado = false;
    protected Rectangle border = new Rectangle(TAM_CASILLERO,TAM_CASILLERO);

    public VistaCasillero(Casillero casillero, CaminoController caminoController, PersonajeController personajeController, VistaInfo info) {
        this.casillero = casillero;
        this.caminoController = caminoController;
        this.personajeController = personajeController;
        this.info = info;
        border.toBack();
        border.setFill(Color.LIGHTGRAY);
        border.setStroke(Color.GRAY);
        border.setOpacity(0.9);
        border.setOnMouseClicked(e -> {
            caminoController.seleccionarCasillero(casillero);
            if (seleccionado) {
                reestablecerCasillero();
                return;
            }
            border.setFill(Color.DARKSEAGREEN);
            seleccionado = true;

        });
        this.getChildren().add(border);
        this.agregarPersonaje(casillero);
        this.agregarConsumible(casillero);
    }

    private void agregarPersonaje(Casillero casillero) {

        if (casillero.getPersonaje() == null) {
            personaje = null;
            return;
        }
        personaje = new VistaPersonaje(casillero.getPersonaje(), personajeController, info);
        this.getChildren().add(personaje);
    }

    private void agregarConsumible(Casillero casillero) {
        if (casillero.getConsumible() == null) {
            consumible = null;
            return;
        }
        consumible = new VistaConsumible(casillero.getConsumible(), this, caminoController);
        this.getChildren().add(consumible);
    }

    protected void reestablecerCasillero() {
        border.setFill(Color.LIGHTGRAY);
        seleccionado = false;

    }

    public void actualizar() {
        this.getChildren().remove(personaje);
        this.getChildren().remove(consumible);
        personaje = null;
        consumible = null;
        reestablecerCasillero();
        this.agregarPersonaje(casillero);
        this.agregarConsumible(casillero);
    }

    protected Casillero getCasillero() {
        return casillero;
    }
}
