package controlador;


import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.tablero.Camino;
import modelo.tablero.Casillero;

import java.util.ArrayList;

public class CaminoController {

    private final ArrayList<Casillero> listaCasilleros;

    public CaminoController() {
        this.listaCasilleros = new ArrayList<Casillero>();
    }

    public void seleccionarCasillero(Casillero unCasillero) {
        if (listaCasilleros.contains(unCasillero)) {
            deshacerSeleccionCasillero(unCasillero);
            return;
        }
        listaCasilleros.add(unCasillero);
    }

    public Camino obtenerCamino() throws CaminoInvalidoException {
        Camino camino = new Camino(listaCasilleros);
        return camino;
    }

    public void limpiar() {
        listaCasilleros.clear();
    }

    private void deshacerSeleccionCasillero(Casillero unCasillero) {
        listaCasilleros.remove(unCasillero);
    }
}
