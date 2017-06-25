package vista;

import controlador.CaminoController;
import controlador.PersonajeController;
import controlador.SeleccionarHandler;
import javafx.scene.layout.GridPane;
import modelo.partida.Partida;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;

public class VistaTablero extends GridPane {
    //private final SeleccionarHandler seleccionarHandler;
    private final CaminoController caminoController;
    private final PersonajeController personajeController;
    private final VistaInfo info;
    private VistaCasillero[][] tablero;
    private Partida partida;
    private int X_CASILLEROS;
    private int Y_CASILLEROS;

    public VistaTablero(Partida partida, CaminoController caminoController, PersonajeController personajeController, VistaInfo info) {
        this.partida = partida;
        this.caminoController = caminoController;
        this.personajeController = personajeController;
        this.info = info;
        this.X_CASILLEROS = partida.getTablero().getDimensionX();
        this.Y_CASILLEROS = partida.getTablero().getDimensionY();
        this.tablero = new VistaCasillero[X_CASILLEROS][Y_CASILLEROS];
        this.crearCasilleros();
    }

    private void crearCasilleros() {
        for (int y = 0; y < Y_CASILLEROS; y++) {
            for (int x = 0; x < X_CASILLEROS; x++) {
                Casillero casillero = partida.getTablero().getCasillero(new Posicion(x+1,y+1));
                tablero[x][y] = new VistaCasillero(casillero, caminoController, personajeController, info);
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
