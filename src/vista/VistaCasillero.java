package vista;


import controlador.CaminoController;
import controlador.PersonajeController;
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
    //private final SeleccionarHandler seleccionarHandler;
    private final CaminoController caminoController;
    private final PersonajeController personajeController;
    private VistaPersonaje personaje;
    private VistaConsumible consumible;
    protected static final int TAM_CASILLERO = 40;
    protected boolean seleccionado = false;
    protected Rectangle border = new Rectangle(TAM_CASILLERO,TAM_CASILLERO);

    public VistaCasillero(Casillero casillero, CaminoController caminoController, PersonajeController personajeController) {
        this.casillero = casillero;
        this.caminoController = caminoController;
        this.personajeController = personajeController;
        //this.seleccionarHandler = seleccionarHandler;
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
            /*try {
                seleccionarHandler.seleccionarCasillero(new Posicion(x, y));
                if (seleccionado) {
                    reestablecerCasillero();
                    return;
                }
                border.setFill(Color.DARKSEAGREEN);
                seleccionado = true;
            } catch (NoSeSeleccionoNingunPersonajeException ex){
                ex.printStackTrace();
            }*/
        });
        this.getChildren().add(border);
        this.agregarPersonaje(casillero);
    }

    private void agregarPersonaje(Casillero casillero) {
        //this.getChildren().remove(personaje);
        if (casillero.getPersonaje() == null) {
            personaje = null;
            return;
        }
        personaje = new VistaPersonaje(casillero.getPersonaje(), personajeController);
        this.getChildren().add(personaje);
    }

    private void agregarConsumible(Casillero casillero) {
        //this.getChildren().remove(consumible);
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
