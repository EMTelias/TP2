package vista;


import controlador.SeleccionarHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modelo.excepciones.personaje.NoSeSeleccionoNingunPersonajeException;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;

public class VistaCasillero extends Pane {
    private final Casillero casillero;
    private final SeleccionarHandler seleccionarHandler;
    private VistaPersonaje personaje;
    protected static final int TAM_CASILLERO = 40;
    private boolean seleccionado = false;
    private int x;
    private int y;
    private Rectangle border = new Rectangle(TAM_CASILLERO,TAM_CASILLERO);

    public VistaCasillero(Casillero casillero, int x, int y, SeleccionarHandler seleccionarHandler) {
        this.x = x;
        this.y = y;
        this.casillero = casillero;
        this.seleccionarHandler = seleccionarHandler;
        border.toBack();
        border.setFill(Color.LIGHTGRAY);
        border.setStroke(Color.GRAY);
        border.setOpacity(0.9);
        border.setOnMouseClicked(e -> {
            try {
                seleccionarHandler.seleccionarCasillero(new Posicion(x, y));
                if (seleccionado) {
                    reestablecerCasillero();
                    return;
                }
                border.setFill(Color.DARKSEAGREEN);
                seleccionado = true;
            } catch (NoSeSeleccionoNingunPersonajeException ex){
                ex.printStackTrace();
            }
        });
        this.getChildren().add(border);
        this.agregarPersonaje(casillero);
    }

    private void agregarPersonaje(Casillero casillero) {
        this.getChildren().remove(personaje);
        if (casillero.getPersonaje() == null) {
            personaje = null;
            return;
        }
        personaje = new VistaPersonaje(casillero.getPersonaje(), x, y, seleccionarHandler);
        this.getChildren().add(personaje);
    }

    private void reestablecerCasillero() {
        border.setFill(Color.LIGHTGRAY);
        seleccionado = false;

    }

    public void actualizar() {
        this.getChildren().remove(personaje);
        personaje = null;
        reestablecerCasillero();
        this.agregarPersonaje(casillero);
    }
}
