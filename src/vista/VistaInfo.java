package vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Created by jalt on 24/06/17.
 */
public class VistaInfo extends VBox{

    private HBox turnos = new HBox();
    private HBox ataques = new HBox();
    private HBox movimientos = new HBox();
    private HBox imagen = new HBox();
    private HBox vida = new HBox();
    private HBox ki = new HBox();
    private ImageView imageView = new ImageView();
    private Text turnoActualText = new Text();
    private Text ataquesRestText = new Text();
    private Text movRestText = new Text();
    private Text vidaActualText = new Text();
    private Text kiActualText = new Text();
    private String imgDir = "vista/imagenes/";
    private String imgExt = ".png";


    public VistaInfo() {
        Text turnoText = new Text("Turno: ");
        turnos.getChildren().addAll(turnoText, turnoActualText);
        Text ataquesText = new Text("Ataques Restantes: ");
        ataques.getChildren().addAll(ataquesText, ataquesRestText);
        Text movimientosText = new Text("Movimientos Restantes: ");
        movimientos.getChildren().addAll(movimientosText, movRestText);
        imagen.getChildren().add(imageView);
        Text vidaText = new Text("Vida actual: ");
        vida.getChildren().addAll(vidaText, vidaActualText);
        Text kiText = new Text("Ki actual: ");
        ki.getChildren().addAll(kiText, kiActualText);
        setDefault();
        this.getChildren().addAll(turnos, ataques, movimientos, imagen, vida, ki);

    }

    private void setImagenDefault() {
        Image imagen = new Image(imgDir + "Default" + imgExt);
        imageView.toFront();
        imageView.setFitHeight(192);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setImage(imagen);
    }

    public void setDefault() {
        setTurno("--");
        setAtaques("--");
        setMovimientos("--");
        setImagenDefault();
        setVidaActual("--");
        setKiActual("--");
    }

    public void setTurno(String turnoText) {
        turnoActualText.setText(turnoText);
    }

    public void setAtaques(String ataquesRest) {
        ataquesRestText.setText(ataquesRest);
    }

    public void setMovimientos(String movRest) {
        movRestText.setText(movRest);
    }

    public void setVidaActual(String vidaActual) {
        vidaActualText.setText(vidaActual);
    }

    public void setKiActual(String kiActual) {
        kiActualText.setText(kiActual);
    }

    public void setImagen(String nombreImagen) {
        Image nuevaImagen = new Image(imgDir + nombreImagen + imgExt);
        imageView.setImage(nuevaImagen);
    }

}
