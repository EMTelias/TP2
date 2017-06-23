package controlador;

import modelo.excepciones.controlador.NoSePuedeSeleccionarMasDeDosPersonajesException;
import modelo.excepciones.personaje.NoSeSeleccionoNingunPersonajeException;
import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.partida.Partida;
import modelo.tablero.Camino;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SeleccionarHandler {
    private LinkedList<Posicion> colaPosiciones;
    private List<Casillero> casillerosSeleccionados;
    private Partida partida;

    public SeleccionarHandler(Partida unaPartida){
        casillerosSeleccionados = new ArrayList<>();
        partida = unaPartida;
        colaPosiciones = new LinkedList<>();
    }


    public void seleccionarCasillero(Posicion posicion){
        if ( colaPosiciones.size() == 0 ){ throw new NoSeSeleccionoNingunPersonajeException(); }
        else{
            Casillero casillero = partida.getTablero().getCasillero(posicion);

            //Si ya lo tenia seleccionado:
            if(casillerosSeleccionados.contains(casillero)) { casillerosSeleccionados.remove(casillero); return;}
            casillerosSeleccionados.add(casillero);
        }
    }

    public void seleccionarPersonaje(Posicion posicion){
        if (colaPosiciones.size() == 2) {
            throw new NoSePuedeSeleccionarMasDeDosPersonajesException();
        }
        colaPosiciones.addLast(posicion);
    }

    public Posicion getPosicionPersonajeSeleccionado() {
        return colaPosiciones.getFirst();
    }

    public void limpiar() {
        colaPosiciones.clear();
        casillerosSeleccionados.clear();
    }


    public Camino getCaminoSeleccionado() throws CaminoInvalidoException {
        return (new Camino(casillerosSeleccionados));
    }

    public void deseleccionarPersonaje(Posicion posicion){
        LinkedList<Posicion> cola_aux = new LinkedList<Posicion>();
        while (colaPosiciones.size() != 0){
            Posicion p = colaPosiciones.removeFirst();
            if(!p.esIgual(posicion)){cola_aux.addLast(p);}
        }
    }
}
