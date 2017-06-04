package view;


import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import sun.rmi.runtime.Log;


import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CasilleroView extends Rectangle {

    public static final String CASILLERO_IMG = "casillero";
    public static final String IMG_DIR = "img";
    public static final String IMG_EXT = ".jpg";

    public CasilleroView(int x, int y){
        Path path = Paths.get(IMG_DIR);
        String imageDirectory = path.toAbsolutePath().toUri().toString();

        Image img = new Image(imageDirectory + CASILLERO_IMG + IMG_EXT, 50, 50, false, false);

        setFill(new ImagePattern(img));
        setStroke(Color.BLACK);

        setWidth(AlgoBallView.TAM_CASILLERO);
        setHeight(AlgoBallView.TAM_CASILLERO);

        relocate(x * AlgoBallView.TAM_CASILLERO, y * AlgoBallView.TAM_CASILLERO);

    }
}
