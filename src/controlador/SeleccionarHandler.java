package controlador;

import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.partida.Partida;
import modelo.tablero.Camino;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;
import vista.Piece;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarHandler {
    private Posicion posicionPersonajeSeleccionado1;
    private Posicion posicionPersonajeSeleccionado2;
    private List<Casillero> casillerosSeleccionados;
    private Partida partida;
    private Piece piezaAMover;

    public SeleccionarHandler(Partida unaPartida){
        posicionPersonajeSeleccionado1=null;
        posicionPersonajeSeleccionado2=null;
        casillerosSeleccionados = new ArrayList<>();
        partida = unaPartida;
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
        Casillero casillero = partida.getTablero().getCasillero(posicion);
        //System.out.println(casillerosSeleccionados.size());

        //Si ya lo tenia seleccionado:
        if(casillerosSeleccionados.contains(casillero)) { casillerosSeleccionados.remove(casillero); return;}
        casillerosSeleccionados.add(casillero);
    }

    public void seleccionarPersonaje(Posicion posicion){
        //System.out.println("posicion clickeada: "+posicion.getPosicionX()+","+posicion.getPosicionY());
        //System.out.println("posicion 1: "+posicionPersonajeSeleccionado1);
        //System.out.println("posicion 2: "+posicionPersonajeSeleccionado2);

        //Deseleccionar:(no hace falta.. es dificil compararlos)
        //if (posicion == posicionPersonajeSeleccionado1) { posicionPersonajeSeleccionado1 = null; System.out.println("Deseleccionando Personaje 1");return;}
        //if (posicion == posicionPersonajeSeleccionado2) { posicionPersonajeSeleccionado2 = null; System.out.println("Deseleccionando Personaje 2");return;}

        //Lleno personaje1 y luego personaje2, segun esten vacios.. si los 2 estan llenos reemplazo el primero
        if(this.posicionPersonaje1NoSeleccionada()){ posicionPersonajeSeleccionado1 = posicion;System.out.println("Personaje Seleccionado: "+partida.personajeEnPosicion(posicion));return;}
        if(this.posicionPersonaje2NoSeleccionada()){ posicionPersonajeSeleccionado2 = posicion;System.out.println("Personaje Seleccionado: "+partida.personajeEnPosicion(posicion));return;}
        if(this.yaTengo2PosicionesDePersonajesSeleccionadas()){
            posicionPersonajeSeleccionado1 = posicion;
            System.out.println("Personaje Seleccionado: "+partida.personajeEnPosicion(posicion));
            return;
        }



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
}
