package partida;

import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import jugador.Jugador;
import tablero.Tablero;


public class Partida {
    private static final int DIM_ALTO = 10;
    private static final int DIM_ANCHO = 10;

    private Tablero tablero;

    private boolean estadoDePartida;

    private Turno turno;


    public Partida(Jugador guerrerosZ,Jugador enemigos) throws DimensionDeTableroInvalidoException, CasilleroOcupadoException {

        this.tablero = new Tablero(DIM_ANCHO,DIM_ALTO);
        this.tablero.initDePersonajes(guerrerosZ.getPersonajes(),enemigos.getPersonajes());

        this.turno = new Turno(guerrerosZ,enemigos);
        this.estadoDePartida = true;

    }



}

