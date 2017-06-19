package controlador;

import modelo.excepciones.controlador.NoSePuedeSeleccionarMasDeDosPersonajesException;
import modelo.excepciones.personaje.NoSePuedeSeleccionarDosPersonajesDelMismoEquipo;
import modelo.excepciones.personaje.NoSeSeleccionoNingunPersonajeException;
import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.partida.Partida;
import modelo.tablero.Camino;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;
import vista.Piece;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SeleccionarHandler {
    private Posicion posicionPersonajeSeleccionado1;
    private Posicion posicionPersonajeSeleccionado2;
    private LinkedList<Posicion> colaPosiciones;
    private List<Casillero> casillerosSeleccionados;
    private Partida partida;
    private Piece piezaAMover;

    public SeleccionarHandler(Partida unaPartida){
        posicionPersonajeSeleccionado1=null;
        posicionPersonajeSeleccionado2=null;
        casillerosSeleccionados = new ArrayList<>();
        partida = unaPartida;
        colaPosiciones = new LinkedList<>();
    }

    private boolean yaTengo2PosicionesDePersonajesSeleccionadas(){
        return ((posicionPersonajeSeleccionado1 != null)&&(posicionPersonajeSeleccionado2 != null));
    }

    private boolean posicionPersonaje1NoSeleccionada(){
        return(posicionPersonajeSeleccionado1 == null);
    }

    private boolean posicionPersonaje2NoSeleccionada(){
        return(posicionPersonajeSeleccionado2 == null);
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
        }else if ( colaPosiciones.size() == 1 ){
            Posicion p = colaPosiciones.getFirst();
            colaPosiciones.addFirst(p);
            if (partida.personajeEnPosicion(p).getEquipo() == partida.personajeEnPosicion(posicion).getEquipo()){
                throw new NoSePuedeSeleccionarDosPersonajesDelMismoEquipo();
            }
        }
        colaPosiciones.addLast(posicion);
    }

    public Posicion getPosicionPersonajeSeleccionado() {
        return colaPosiciones.getFirst();
    }

    public void limpiar() {
        posicionPersonajeSeleccionado1 = null;
        posicionPersonajeSeleccionado2 = null;
        colaPosiciones.clear();
        casillerosSeleccionados.clear();
    }


    public Posicion getPosicionPersonajeSeleccionado1() {
        return posicionPersonajeSeleccionado1;
    }

    public Posicion getPosicionPersonajeSeleccionado2() {
        return posicionPersonajeSeleccionado2;
    }

    public Camino getCaminoSeleccionado() throws CaminoInvalidoException {
        return (new Camino(casillerosSeleccionados));
    }


    public void setPiezaAMover(Piece piece) {
        piezaAMover = piece;
    }

    public Piece getPiezaAMover() {
        return piezaAMover;
    }

    public void deseleccionarTodosLosCasilleros() {
        casillerosSeleccionados.clear();
    }

    public void deseleccionarPersonajes() {
        posicionPersonajeSeleccionado1 = null;
        posicionPersonajeSeleccionado2 = null;
    }
    public void deseleccionarPersonaje(Posicion posicion){
        LinkedList<Posicion> cola_aux = new LinkedList<Posicion>();
        while (colaPosiciones.size() != 0){
            Posicion p = colaPosiciones.removeFirst();
            if(!p.esIgual(posicion)){cola_aux.addLast(p);}
        }
    }
}
