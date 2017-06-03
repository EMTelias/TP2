package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AlgoBallView extends Application {

    public static final int TAM_CASILLERO = 60;
    public static final int ANCHO_TABLERO = 10;
    public static final int ALTO_TABLERO = 10;

    private CasilleroView[][] tablero = new CasilleroView[ANCHO_TABLERO][ALTO_TABLERO];

    private Group casilleros  = new Group();

    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(crearContenido());

        primaryStage.setTitle("AlgoBall");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private Parent crearContenido() {

        int x;
        int y;

        Pane root = new Pane();
        root.setPrefSize(ANCHO_TABLERO * TAM_CASILLERO, ALTO_TABLERO * TAM_CASILLERO);
        root.getChildren().addAll(casilleros);

        for (y = 0; y < ALTO_TABLERO; y++) {
            for (x = 0; x < ANCHO_TABLERO; x++) {

                CasilleroView casillero = new CasilleroView(x, y);
                tablero[x][y] = casillero;
                casilleros.getChildren().add(casillero);

            }
        }
        return root;
    }

}
