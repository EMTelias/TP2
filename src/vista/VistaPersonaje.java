package vista;

import controlador.SeleccionarHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import modelo.excepciones.controlador.NoSePuedeSeleccionarMasDeDosPersonajesException;
import modelo.excepciones.personaje.NoSePuedeSeleccionarDosPersonajesDelMismoEquipo;
import modelo.personaje.Personaje;
import modelo.tablero.Posicion;

public class VistaPersonaje extends BorderPane{

    private final Personaje personaje;
    private final int x;
    private final int y;
    private String imgDir = "vista/imagenes/";
    private String imgExt = ".png";
    private boolean seleccionado = false;

    public VistaPersonaje(Personaje unPersonaje, int x, int y, SeleccionarHandler seleccionarHandler) {
        this.personaje = unPersonaje;
        this.x = x;
        this.y = y;
        Image imagen = new Image(imgDir + unPersonaje.getClass().getSimpleName() + imgExt);
        ImageView imageView = new ImageView();
        imageView.toFront();
        imageView.setFitHeight(VistaCasillero.TAM_CASILLERO);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setImage(imagen);
        imageView.setOnMouseClicked(e-> {
            try {
                seleccionarHandler.seleccionarPersonaje(new Posicion(x, y));
                if (seleccionado) {
                    imageView.setEffect(null);
                    seleccionado = false;
                    return;
                }
                imageView.setEffect(new Glow(0.8));
                seleccionado = true;
            } catch (NoSePuedeSeleccionarMasDeDosPersonajesException e1) {
                e1.printStackTrace();
            } catch (NoSePuedeSeleccionarDosPersonajesDelMismoEquipo e2){
                e2.printStackTrace();
            }
        });
        //contenedor.getChildren().add(imageView);
        this.getChildren().add(imageView);
    }
}
