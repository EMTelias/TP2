package vista;

import controlador.PersonajeController;
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
    private boolean seleccionado = false;

    public VistaPersonaje(Personaje unPersonaje, PersonajeController personajeController) {
        this.personaje = unPersonaje;
        String imgDir = "vista/imagenes/";
        String imgExt = ".png";
        Image imagen = new Image(imgDir + unPersonaje.getClass().getSimpleName() + imgExt);
        ImageView imageView = new ImageView();
        imageView.toFront();
        imageView.setFitHeight(VistaCasillero.TAM_CASILLERO);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setImage(imagen);
        imageView.setOnMouseClicked(e-> {
            try {
                personajeController.seleccionarPersonaje(personaje);
                if (seleccionado) {
                    //seleccionarHandler.deseleccionarPersonaje(new Posicion(x,y));
                    imageView.setEffect(null);
                    seleccionado = false;
                    return;
                }/*else{
                    seleccionarHandler.seleccionarPersonaje(new Posicion(x, y));
                }*/
                imageView.setEffect(new Glow(0.8));
                seleccionado = true;

            } catch (NoSePuedeSeleccionarMasDeDosPersonajesException e1) {
                e1.printStackTrace();
            }/* catch (NoSePuedeSeleccionarDosPersonajesDelMismoEquipo e2){
                e2.printStackTrace();
            }*/

        });
        this.getChildren().add(imageView);
    }
}
