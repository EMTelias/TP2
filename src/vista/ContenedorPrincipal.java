package vista;

import controlador.CaminoController;
import controlador.PersonajeController;
import controlador.eventos.*;
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
import modelo.Consumibles.GeneradorDeConsumiblesRandom;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.excepciones.tablero.DimensionDeTableroInvalidoException;
import modelo.partida.Partida;

public class ContenedorPrincipal extends BorderPane {

    private BarraDeMenu menuBar;
    private VistaTablero vistaTablero;
    private GridPane contenedorCentral;
    private Label consola;
    
    public ContenedorPrincipal(Stage stage, Aplicacion app) throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {

        Partida partida = crearModelo();
        CaminoController caminoController = new CaminoController();
        PersonajeController personajeController = new PersonajeController();

        VistaInfo info = new VistaInfo();
        info.setTurno(partida.turnoActual().getNombre());
        info.setAtaques("1");
        info.setMovimientos("1");
        this.setMenu(stage);
        this.setCentro(partida, caminoController, personajeController, info);
        this.setConsola();
        this.setBotonera(app, partida, caminoController, personajeController, info);
    }

    private void setBotonera(Aplicacion app, Partida partida, CaminoController caminoController, PersonajeController personajeController, VistaInfo info) {

        Button botonMover = new Button();
        botonMover.setText("Mover");
        botonMover.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonMoverHandler botonMoverHandler = new BotonMoverHandler(app, vistaTablero, partida, caminoController, personajeController, info, consola);
        botonMover.setOnAction(botonMoverHandler);

        Button botonAtaqueBasico = new Button();
        botonAtaqueBasico.setText("Ataque basico");
        botonAtaqueBasico.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonAtaqueBasicoHandler botonAtaqueBasicoHandler = new BotonAtaqueBasicoHandler(app, vistaTablero, partida, caminoController, personajeController, info, consola);
        botonAtaqueBasico.setOnAction(botonAtaqueBasicoHandler);

        Button botonAtaqueEspecial = new Button();
        botonAtaqueEspecial.setText("Ataque especial");
        botonAtaqueEspecial.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonAtaqueEspecialHandler botonAtaqueEspecialHandler = new BotonAtaqueEspecialHandler(app, vistaTablero, partida, caminoController, personajeController, info, consola);
        botonAtaqueEspecial.setOnAction(botonAtaqueEspecialHandler);

        Button botonTransformar = new Button();
        botonTransformar.setText("Transformar");
        botonTransformar.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonTransformarHandler botonTransformarHandler = new BotonTransformarHandler(vistaTablero, partida, caminoController, personajeController, info, consola);
        botonTransformar.setOnAction(botonTransformarHandler);

        Button botonPasar = new Button();
        botonPasar.setText("Pasar");
        botonPasar.setStyle("-fx-font: 16 arial; -fx-base: #ee2211;");
        BotonPasarHandler botonPasarHandler = new BotonPasarHandler(partida, vistaTablero, caminoController, personajeController, info, consola);
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

    private Partida crearModelo() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        GeneradorDeConsumiblesRandom generador = new GeneradorDeConsumiblesRandom();
        Partida partida = new Partida();
        partida.setGeneradorDeConsumibles(generador);
        return partida;
    }
}
