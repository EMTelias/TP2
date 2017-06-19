package vista;


import controlador.SeleccionarHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import modelo.tablero.Posicion;


public class Piece extends StackPane {

    private int oldX, oldY;
    private int xCoord,yCoord;
    private SeleccionarHandler seleccionar;


    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public Piece(String imgDir, int x, int y,SeleccionarHandler seleccionarHandler) {

        xCoord=x;
        yCoord=y;
        seleccionar = seleccionarHandler;
        move(x, y);

        Image imageCharacter = new Image(imgDir);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(Tile.TILE_SIZE);
        imageView.setPreserveRatio(true);
        imageView.setImage(imageCharacter);

        getChildren().add(imageView);

        setOnMouseClicked(e -> {
            seleccionar.seleccionarPersonaje(new Posicion(xCoord+1,yCoord+1));
            return;
        });

    }

    public void move(int x, int y) {
        oldX = x * Tile.TILE_SIZE;
        oldY = y * Tile.TILE_SIZE;
        relocate(oldX, oldY);
    }

}
