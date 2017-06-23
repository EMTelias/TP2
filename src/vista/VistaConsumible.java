package vista;

import controlador.SeleccionarHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import modelo.Consumibles.Consumible;
import modelo.excepciones.controlador.NoSePuedeSeleccionarMasDeDosPersonajesException;
import modelo.tablero.Posicion;


public class VistaConsumible extends BorderPane {

    private final Consumible consumible;
    private final int x;
    private final int y;
    private String imgDir = "vista/imagenes/";
    private String imgExt = ".png";

    public VistaConsumible(Consumible unConsumible, int x, int y, SeleccionarHandler seleccionarHandler) {
        this.consumible = unConsumible;
        this.x = x;
        this.y = y;
        System.out.println("hola");
        Image imagen = new Image(imgDir + unConsumible.getClass().getSimpleName() + imgExt);
        ImageView imageView = new ImageView();
        imageView.toFront();
        imageView.setFitHeight(VistaCasillero.TAM_CASILLERO);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setImage(imagen);
        imageView.setOnMouseClicked(e-> {
            try {
                seleccionarHandler.seleccionarCasillero(new Posicion(x, y));

            } catch (NoSePuedeSeleccionarMasDeDosPersonajesException e1) {
                e1.printStackTrace();
            }

        });
        this.getChildren().add(imageView);
    }
}


