package controlador;


import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.excepciones.tablero.CasilleroNoContiguoException;
import modelo.tablero.Camino;
import modelo.tablero.Casillero;

import java.util.ArrayList;

public class CaminoController {

    private final ArrayList<Casillero> listaCasilleros;

    public CaminoController() {
        this.listaCasilleros = new ArrayList<Casillero>();
    }

    public void seleccionarCasillero(Casillero unCasillero) {
        //Si no es el primer casillero en seleccionar y su distancia al anterior es mayor que 1: no son contiguos
        if( (listaCasilleros.size()>0) && (unCasillero.distanciaHasta(listaCasilleros.get(listaCasilleros.size()-1)) >1) ){
            throw new CasilleroNoContiguoException();
        }

        if (listaCasilleros.contains(unCasillero)) {
            deshacerSeleccionCasillero(unCasillero);
        }else {
            listaCasilleros.add(unCasillero);
        }
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
