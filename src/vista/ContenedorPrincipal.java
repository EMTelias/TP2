package vista;

import controlador.SeleccionarHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import modelo.partida.Partida;
import controlador.eventos.*;

public class ContenedorPrincipal extends BorderPane {

    BarraDeMenu menuBar;
    VistaTablero vistaTablero;
    GridPane gridCentral;
    GridPane contenedorCentral;

    public ContenedorPrincipal(Stage stage, Partida partida, SeleccionarHandler seleccionarHandler) {
        this.setMenu(stage);
        this.setCentro(partida, seleccionarHandler);
        this.setConsola();
        this.setBotonera(partida, seleccionarHandler);
    }

    private void setBotonera(Partida partida, SeleccionarHandler seleccionarHandler) {

        Button botonMover = new Button();
        botonMover.setText("Mover");
        botonMover.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonMoverHandler botonMoverHandler = new BotonMoverHandler(vistaTablero, partida, seleccionarHandler);
        botonMover.setOnAction(botonMoverHandler);

        Button botonAtaqueBasico = new Button();
        botonAtaqueBasico.setText("Ataque basico");
        botonAtaqueBasico.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonAtaqueBasicoHandler botonAtaqueBasicoHandler = new BotonAtaqueBasicoHandler(vistaTablero, partida, seleccionarHandler);
        botonAtaqueBasico.setOnAction(botonAtaqueBasicoHandler);

        Button botonAtaqueEspecial = new Button();
        botonAtaqueEspecial.setText("Ataque especial");
        botonAtaqueEspecial.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonAtaqueEspecialHandler botonAtaqueEspecialHandler = new BotonAtaqueEspecialHandler(vistaTablero, partida, seleccionarHandler);
        botonAtaqueEspecial.setOnAction(botonAtaqueEspecialHandler);

        Button botonTransformar = new Button();
        botonTransformar.setText("Transformar");
        botonTransformar.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonTransformarHandler botonTransformarHandler = new BotonTransformarHandler(vistaTablero, partida, seleccionarHandler);
        botonTransformar.setOnAction(botonTransformarHandler);

        Button botonPasar = new Button();
        botonPasar.setText("Pasar");
        botonPasar.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonPasarHandler botonPasarHandler = new BotonPasarHandler(partida, vistaTablero);
        botonPasar.setOnAction(botonPasarHandler);

        VBox contenedorVertical = new VBox(botonMover, botonAtaqueBasico, botonAtaqueEspecial, botonTransformar, botonPasar);
        contenedorVertical.setSpacing(10);
        contenedorVertical.setPadding(new Insets(15));

        this.setLeft(contenedorVertical);

    }

    private void setMenu(Stage stage) {
        this.menuBar = new BarraDeMenu(stage);
        this.setTop(menuBar);
    }

    private void setCentro(Partida partida, SeleccionarHandler seleccionarHandler) {


        vistaTablero = new VistaTablero(partida, seleccionarHandler);
        //vistaRobot.dibujar();

        contenedorCentral = vistaTablero;
        contenedorCentral.setAlignment(Pos.CENTER);
        //contenedorCentral.setSpacing(20);
        //contenedorCentral.setPadding(new Insets(25));
        Image imagen = new Image("file:src/vista/imagenes/fondo.jpg");
        BackgroundImage imagenDeFondo = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        contenedorCentral.setBackground(new Background(imagenDeFondo));

        this.setCenter(contenedorCentral);
    }

    private void setConsola() {

        // TODO cambiar por el modelo de Consola...
        Label etiqueta = new Label();
        etiqueta.setText("consola...");
        etiqueta.setFont(Font.font("courier new", FontWeight.SEMI_BOLD, 14));
        etiqueta.setTextFill(Color.WHITE);

        VBox contenedorConsola = new VBox(etiqueta);
        contenedorConsola.setSpacing(10);
        contenedorConsola.setPadding(new Insets(15));
        contenedorConsola.setStyle("-fx-background-color: black;");

        this.setBottom(contenedorConsola);
    }

    public BarraDeMenu getBarraDeMenu() {
        return menuBar;
    }

}
