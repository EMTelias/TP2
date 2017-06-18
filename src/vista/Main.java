package vista;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        Piece gohan = new Piece(imageDirectory + "Gohan" + IMG_EXT, 0,7);
        Piece piccolo = new Piece(imageDirectory + "Piccolo" + IMG_EXT, 0,14);
        Piece cell = new Piece(imageDirectory + "Cell" + IMG_EXT, 14,7);
        Piece freezer = new Piece(imageDirectory + "Freezer" + IMG_EXT, 14,14);
        Piece majinBoo = new Piece(imageDirectory + "MajinBoo" + IMG_EXT, 14,0);

        grid[0][0].setPiece(goku);
        grid[0][7].setPiece(gohan);
        grid[0][14].setPiece(piccolo);
        grid[14][7].setPiece(cell);
        grid[14][14].setPiece(freezer);
        grid[14][0].setPiece(majinBoo);
        pieceGroup.getChildren().addAll(goku, gohan, piccolo, cell, freezer, majinBoo);
    }

    private VBox addButtonsToVBox(VBox vbox) {
        int tamXboton = WIDTH - Tile.TILE_SIZE * X_TILES;
        int tamYboton = Tile.TILE_SIZE;
        vbox.setPrefSize(tamXboton, 4 * tamYboton);
        vbox.setTranslateX(Tile.TILE_SIZE * X_TILES);
        vbox.setTranslateY(Tile.TILE_SIZE * (Y_TILES - 4)); // los 4 botones a agregar


        Button attackB = new Button("Ataque basico");
        attackB.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        attackB.setPrefSize(tamXboton,tamYboton);
        attackB.setMinHeight(tamYboton);
        Button attackS = new Button("Ataque especial");
        attackS.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        attackS.setPrefSize(tamXboton,tamYboton);
        attackS.setMinHeight(tamYboton);
        Button move = new Button("Mover");
        move.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        move.setPrefSize(tamXboton,tamYboton);
        move.setMinHeight(tamYboton);
        Button transform = new Button("Transformar");
        transform.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        transform.setPrefSize(tamXboton,tamYboton);
        transform.setMinHeight(tamYboton);

        vbox.getChildren().addAll(attackB, attackS, move, transform);
        return vbox;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        scene = new Scene(createContent());

        primaryStage.setTitle("Dragon AlgoBall");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
