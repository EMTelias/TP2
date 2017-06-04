package view;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonajeView extends Rectangle {

    public static final String CASILLERO_IMG = "Goku";
    public static final String IMG_DIR = "img";
    public static final String IMG_EXT = ".png";


    public PersonajeView(int x, int y,String nombre){

        Path path = Paths.get(IMG_DIR);
        String imageDirectory = path.toAbsolutePath().toUri().toString();

        Image img = new Image(imageDirectory + CASILLERO_IMG + IMG_EXT, 50, 50, false, false);

        setFill(new ImagePattern(img));
        setStroke(Color.BLACK);

        setWidth(AlgoBallView.TAM_CASILLERO);
        setHeight(AlgoBallView.TAM_CASILLERO);

    }

}
