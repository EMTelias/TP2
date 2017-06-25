package vista;

import controlador.CaminoController;
import controlador.PersonajeController;
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

    private BarraDeMenu menuBar;
    private VistaTablero vistaTablero;
    private GridPane contenedorCentral;
    private Label consola;
    
    public ContenedorPrincipal(Stage stage, Partida partida, CaminoController caminoController, PersonajeController personajeController) {
        VistaInfo info = new VistaInfo();
        this.setMenu(stage);
        this.setCentro(partida, caminoController, personajeController, info);
        this.setConsola();
        this.setBotonera(partida, caminoController, personajeController, info);
    }

    private void setBotonera(Partida partida, CaminoController caminoController, PersonajeController personajeController, VistaInfo info) {

        Button botonMover = new Button();
        botonMover.setText("Mover");
        botonMover.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonMoverHandler botonMoverHandler = new BotonMoverHandler(vistaTablero, partida, caminoController, personajeController,consola);
        botonMover.setOnAction(botonMoverHandler);

        Button botonAtaqueBasico = new Button();
        botonAtaqueBasico.setText("Ataque basico");
        botonAtaqueBasico.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonAtaqueBasicoHandler botonAtaqueBasicoHandler = new BotonAtaqueBasicoHandler(vistaTablero, partida, caminoController, personajeController, consola);
        botonAtaqueBasico.setOnAction(botonAtaqueBasicoHandler);

        Button botonAtaqueEspecial = new Button();
        botonAtaqueEspecial.setText("Ataque especial");
        botonAtaqueEspecial.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonAtaqueEspecialHandler botonAtaqueEspecialHandler = new BotonAtaqueEspecialHandler(vistaTablero, partida, caminoController, personajeController, consola);
        botonAtaqueEspecial.setOnAction(botonAtaqueEspecialHandler);

        Button botonTransformar = new Button();
        botonTransformar.setText("Transformar");
        botonTransformar.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonTransformarHandler botonTransformarHandler = new BotonTransformarHandler(vistaTablero, partida, caminoController, personajeController, consola);
        botonTransformar.setOnAction(botonTransformarHandler);

        Button botonPasar = new Button();
        botonPasar.setText("Pasar");
        botonPasar.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonPasarHandler botonPasarHandler = new BotonPasarHandler(partida, vistaTablero,consola);
        botonPasar.setOnAction(botonPasarHandler);

        VBox contenedorVertical = new VBox(botonMover, botonAtaqueBasico, botonAtaqueEspecial, botonTransformar, botonPasar, info);
        contenedorVertical.setSpacing(10);
        contenedorVertical.setPadding(new Insets(15));

        this.setLeft(contenedorVertical);

    }

    private void setMenu(Stage stage) {
        this.menuBar = new BarraDeMenu(stage);
        this.setTop(menuBar);
    }

    private void setCentro(Partida partida, CaminoController caminoController, PersonajeController personajeController, VistaInfo info) {


        vistaTablero = new VistaTablero(partida, caminoController, personajeController, info);

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
        consola = new Label();
        consola.setText("");
        consola.setFont(Font.font("courier new", FontWeight.SEMI_BOLD, 14));
        consola.setTextFill(Color.WHITE);

        VBox contenedorConsola = new VBox(consola);
        contenedorConsola.setSpacing(10);
        contenedorConsola.setPadding(new Insets(15));
        contenedorConsola.setStyle("-fx-background-color: black;");

        this.setBottom(contenedorConsola);
    }

    public BarraDeMenu getBarraDeMenu() {
        return menuBar;
    }

}
