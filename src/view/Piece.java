package view;


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
        imageView.setFitHeight(40);
        imageView.setPreserveRatio(true);
        imageView.setImage(imageCharacter);

        getChildren().add(imageView);

        setOnMousePressed(e -> {
            // do something
        });

    }

    public void move(int x, int y) {
        oldX = x * 40;
        oldY = y * 40;
        relocate(oldX, oldY);
    }

}
