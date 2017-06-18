package vista;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Piece extends StackPane {

    private double oldX, oldY;


    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    public Piece(String imgDir, int x, int y) {

        move(x, y);

        Image imageCharacter = new Image(imgDir);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(Tile.TILE_SIZE);
        imageView.setPreserveRatio(true);
        imageView.setImage(imageCharacter);

        getChildren().add(imageView);

        setOnMousePressed(e -> {
            // do something
        });

    }

    public void move(int x, int y) {
        oldX = x * Tile.TILE_SIZE;
        oldY = y * Tile.TILE_SIZE;
        relocate(oldX, oldY);
    }

}
