package vista;

import controlador.PersonajeController;
import controlador.SeleccionarHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import modelo.excepciones.controlador.NoSePuedeSeleccionarMasDeDosPersonajesException;
import modelo.excepciones.personaje.NoEsSuTurnoException;
import modelo.excepciones.personaje.NoSePuedeSeleccionarDosPersonajesDelMismoEquipo;
import modelo.personaje.Personaje;
import modelo.tablero.Posicion;

public class VistaPersonaje extends BorderPane{

    private final Personaje personaje;
    private final PersonajeController personajeController;
    private final VistaInfo info;
    private boolean seleccionado = false;

    public VistaPersonaje(Personaje unPersonaje, PersonajeController personajeController, VistaInfo info) {
        this.personaje = unPersonaje;
        this.personajeController = personajeController;
        this.info = info;
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
                modificarPanelInfo();
                if (seleccionado) {
                    imageView.setEffect(null);
                    seleccionado = false;
                    return;
                }
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

    private void modificarPanelInfo() {
        Personaje primerPersonaje = personajeController.verPrimerPersonaje();
        Personaje segundoPersonaje = personajeController.verSegundoPersonaje();
        if (primerPersonaje == personaje) {
            info.setImagen(personaje.getClass().getSimpleName());
            info.setVidaActual(Integer.toString(personaje.getVida()));
            info.setKiActual(Integer.toString(personaje.getKi()));
            info.setDistanciaAtaqueActual(Integer.toString(personaje.getDistanciaAtaque()));
            info.setPoderPeleaActual(Integer.toString(personaje.getPoderDePelea()));
            info.setVelocidadActual(Integer.toString(personaje.getVelocidad()));
            return;
        } else {
            if (segundoPersonaje == personaje) {
                return;
            }
        }
        info.setStatsDefault();
    }
}
