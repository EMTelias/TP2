package vista;

import controlador.SeleccionarHandler;
import javafx.scene.layout.GridPane;
import modelo.partida.Partida;
import modelo.tablero.Posicion;

public class VistaTablero extends GridPane {
    private final SeleccionarHandler seleccionarHandler;
    private VistaCasillero[][] tablero;
    private Partida partida;
    private int X_CASILLEROS;
    private int Y_CASILLEROS;

    public VistaTablero(Partida partida, SeleccionarHandler seleccionarHandler) {
        this.partida = partida;
        this.seleccionarHandler = seleccionarHandler;
        this.X_CASILLEROS = partida.getTablero().getDimensionX();
        this.Y_CASILLEROS = partida.getTablero().getDimensionY();
        this.tablero = new VistaCasillero[X_CASILLEROS][Y_CASILLEROS];
        this.crearCasilleros();
        //System.out.println(grid.getWidth() + grid.getHeight());
    }

    private void crearCasilleros() {
        for (int y = 0; y < Y_CASILLEROS; y++) {
            for (int x = 0; x < X_CASILLEROS; x++) {
                tablero[x][y] = new VistaCasillero(partida.getTablero().getCasillero(new Posicion(x+1,y+1)), x+1, y+1, seleccionarHandler);
                this.add(tablero[x][y], x,y);
            }
        }
    }

    public void actualizarVista() {
        // Actualizo a  los casilleros del tablero porque pueden llegar a aparecer
        // consumibles en algun casillero, ademas de actualizar posiciones de personajes
        for (int y = 0; y < Y_CASILLEROS; y++) {
            for (int x = 0; x < X_CASILLEROS; x++) {
                tablero[x][y].actualizar();
            }
        }
    }
}
