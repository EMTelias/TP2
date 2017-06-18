package vista;

import controlador.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.partida.Partida;
import modelo.personaje.Personaje;
import modelo.tablero.Camino;
import modelo.tablero.Posicion;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int Y_TILES = 15;
    private static final int X_TILES = 15;
    private Tile[][] grid = new Tile[X_TILES][Y_TILES];
    private Scene scene;
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();

    private String IMG_DIR = "img";
    private String IMG_EXT = ".png";
    private Partida partida;
    private Posicion posAtacante;
    private Posicion posAtacado;
    private Camino camino;




    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH,HEIGHT);
        root.getChildren().addAll(tileGroup, pieceGroup);

        for (int y = 0; y < Y_TILES; y++) {
            for (int x = 0; x < X_TILES; x++) {
                Tile tile = new Tile(x, y);
                grid[x][y] = tile;
                tileGroup.getChildren().add(tile);
            }
        }
        addFightersToGrid();

        VBox vbox = new VBox();
        root.getChildren().add(addButtonsToVBox(vbox));

        return root;
    }

    private void addFightersToGrid() {
        Path path = Paths.get(IMG_DIR);
        String imageDirectory = path.toAbsolutePath().toUri().toString();
        Piece goku = new Piece(imageDirectory + "Goku" + IMG_EXT, 0,0);
        Piece gohan = new Piece(imageDirectory + "Gohan" + IMG_EXT, 1,0);
        Piece piccolo = new Piece(imageDirectory + "Piccolo" + IMG_EXT, 0,1);
        Piece cell = new Piece(imageDirectory + "Cell" + IMG_EXT, 14,14);
        Piece freezer = new Piece(imageDirectory + "Freezer" + IMG_EXT, 14,13);
        Piece majinBoo = new Piece(imageDirectory + "MajinBoo" + IMG_EXT, 13,14);

        grid[0][0].setPiece(goku);
        grid[1][0].setPiece(gohan);
        grid[0][1].setPiece(piccolo);
        grid[14][14].setPiece(cell);
        grid[14][13].setPiece(freezer);
        grid[13][14].setPiece(majinBoo);
        pieceGroup.getChildren().addAll(goku, gohan, piccolo, cell, freezer, majinBoo);
    }

    private VBox addButtonsToVBox(VBox vbox) {
        int tamXboton = WIDTH - Tile.TILE_SIZE * X_TILES;
        int tamYboton = Tile.TILE_SIZE;
        vbox.setPrefSize(tamXboton, 5 * tamYboton);
        vbox.setTranslateX(Tile.TILE_SIZE * X_TILES);
        vbox.setTranslateY(Tile.TILE_SIZE * (Y_TILES - 5)); // los 5 botones a agregar


        Button pasar = new Button("Pasar turno");
        pasar.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        pasar.setPrefSize(tamXboton,tamYboton);
        pasar.setMinHeight(tamYboton);
        pasar.setOnMouseClicked(new PasarHandler(partida));

        Button ataqueB = new Button("Ataque basico");
        ataqueB.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        ataqueB.setPrefSize(tamXboton,tamYboton);
        ataqueB.setMinHeight(tamYboton);
        ataqueB.setOnMouseClicked(new AtaqueBasicoHandler(partida, posAtacante, posAtacado));

        Button ataqueE = new Button("Ataque especial");
        ataqueE.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        ataqueE.setPrefSize(tamXboton,tamYboton);
        ataqueE.setMinHeight(tamYboton);
        ataqueE.setOnMouseClicked(new AtaqueEspecialHandler(partida, posAtacante, posAtacado));

        Button mover = new Button("Mover");
        mover.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        mover.setPrefSize(tamXboton,tamYboton);
        mover.setMinHeight(tamYboton);
        mover.setOnMouseClicked(new MoverHandler(partida, posAtacante, camino));

        Button transformar = new Button("Transformar");
        transformar.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        transformar.setPrefSize(tamXboton,tamYboton);
        transformar.setMinHeight(tamYboton);
        transformar.setOnMouseClicked(new TransformarHandler(partida, posAtacante));

        vbox.getChildren().addAll(pasar, ataqueB, ataqueE, mover, transformar);
        return vbox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        partida = new Partida();
        scene = new Scene(createContent());

        primaryStage.setTitle("Dragon AlgoBall");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
