package vista;


import controlador.SeleccionarHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import modelo.tablero.Posicion;

public class TileConsumible extends StackPane{

    private int xCoord,yCoord;
    private SeleccionarHandler seleccionar;



    public TileConsumible(String imgDir, int x, int y,SeleccionarHandler seleccionarHandler) {
        xCoord=x;
        yCoord=y;
        seleccionar = seleccionarHandler;
        relocate(x,y);

        Image imageCharacter = new Image(imgDir);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(Tile.TILE_SIZE);
        imageView.setPreserveRatio(true);
        imageView.setImage(imageCharacter);

        getChildren().add(imageView);

        setOnMouseClicked(e -> {
            System.out.println("Un consumible");
            return;
        });

    }

}

