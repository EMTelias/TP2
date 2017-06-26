package vista;

import controlador.CaminoController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import modelo.Consumibles.Consumible;


public class VistaConsumible extends BorderPane {

    private final Consumible consumible;
    private final VistaCasillero vistaCasillero;
    private final CaminoController caminoController;


    public VistaConsumible(Consumible unConsumible, VistaCasillero vistaCasillero, CaminoController caminoController) {
        this.consumible = unConsumible;
        this.vistaCasillero = vistaCasillero;
        this.caminoController = caminoController;

        String imgDir = "vista/imagenes/";
        String imgExt = ".png";
        Image imagen = new Image(imgDir + unConsumible.getClass().getSimpleName() + imgExt);
        ImageView imageView = new ImageView();
        imageView.toFront();
        imageView.setFitHeight(VistaCasillero.TAM_CASILLERO);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setImage(imagen);
        imageView.setOnMouseClicked(e-> {
            caminoController.seleccionarCasillero(vistaCasillero.getCasillero());
            if (vistaCasillero.seleccionado) {
                vistaCasillero.reestablecerCasillero();
                return;
            }
            vistaCasillero.border.setFill(Color.DARKSEAGREEN);
            vistaCasillero.seleccionado = true;

        });
        this.getChildren().add(imageView);
    }
}


