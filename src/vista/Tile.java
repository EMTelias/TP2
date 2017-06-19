package vista;


import controlador.SeleccionarHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modelo.tablero.Posicion;

public class Tile extends Rectangle {
    private int x, y;
    protected static final int TILE_SIZE = 40;
    private Piece piece;
    private boolean pressed = false;
    private SeleccionarHandler seleccionar;


    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Tile(int x, int y,SeleccionarHandler seleccionarHandler) {
        seleccionar = seleccionarHandler;
        this.x = x;
        this.y = y;
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);
        setStroke(Color.GRAY);
        setFill(Color.LIGHTGRAY);
        relocate(x * TILE_SIZE, y * TILE_SIZE);

        setOnMousePressed(e -> {
            System.out.println(x+","+y);
            seleccionar.seleccionarCasillero(new Posicion(x+1,y+1));

            if (pressed) {
                setFill(Color.LIGHTGRAY);
                pressed = false;
                return;
            }else{
                setFill(Color.DARKSEAGREEN);
                pressed = true;
            }
        });

    }
}
