package vista;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class VistaInfo extends VBox{

    private HBox turnos = new HBox();
    private HBox ataques = new HBox();
    private HBox movimientos = new HBox();
    private HBox imagen = new HBox();
    private HBox vida = new HBox();
    private HBox ki = new HBox();
    private HBox distanciaAtaque = new HBox();
    private HBox poderPelea = new HBox();
    private HBox velocidad = new HBox();
    private ImageView imageView = new ImageView();
    private Text turnoActualText = new Text();
    private Text ataquesRestText = new Text();
    private Text movRestText = new Text();
    private Text vidaActualText = new Text();
    private Text kiActualText = new Text();
    private Text distAtaqueActualText = new Text();
    private Text poderPeleaActualText = new Text();
    private Text velocidadActualText = new Text();
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
        Text poderPeleaText = new Text("Poder de pelea: ");
        poderPelea.getChildren().addAll(poderPeleaText, poderPeleaActualText);
        Text distAtaqueText = new Text("Distancia de ataque: ");
        distanciaAtaque.getChildren().addAll(distAtaqueText, distAtaqueActualText);
        Text velocidadText = new Text("Velocidad: ");
        velocidad.getChildren().addAll(velocidadText, velocidadActualText);
        setDefault();
        this.getChildren().addAll(turnos, ataques, movimientos, imagen, vida, ki, poderPelea, distanciaAtaque, velocidad);

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
        setAtaques("1");
        setMovimientos("1");
        setImagenDefault();
        setVidaActual("--");
        setKiActual("--");
        setDistanciaAtaqueActual("--");
        setPoderPeleaActual("--");
        setVelocidadActual("--");
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

    public void setPoderPeleaActual(String poderPeleaActual) {
        poderPeleaActualText.setText(poderPeleaActual);
    }

    public void setDistanciaAtaqueActual(String distanciaAtaqueActual) {
        distAtaqueActualText.setText(distanciaAtaqueActual);
    }

    public void setVelocidadActual(String velocidadActual) {
        velocidadActualText.setText(velocidadActual);
    }

    public void setImagen(String nombreImagen) {
        Image nuevaImagen = new Image(imgDir + nombreImagen + imgExt);
        imageView.setImage(nuevaImagen);
    }

}
