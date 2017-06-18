package vista;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    private int x, y;
    protected static final int TILE_SIZE = 40;
    private Piece piece;
    private boolean pressed = false;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        setWidth(TILE_SIZE);
        setHeight(TILE_SIZE);
        setStroke(Color.GRAY);
        setFill(Color.LIGHTGRAY);
        relocate(x * TILE_SIZE, y * TILE_SIZE);

        setOnMousePressed(e -> {
            if (pressed) {
                setFill(Color.LIGHTGRAY);
                pressed = false;
                return;
            }
            setFill(Color.DARKSEAGREEN);
            pressed = true;
        });


    }
}
