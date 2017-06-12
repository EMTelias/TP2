package partida;

import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import jugador.Jugador;
import personaje.Personaje;
import tablero.Casillero;
import tablero.Posicion;
import tablero.Tablero;


public class Partida {
    private static final int DIM_ALTO = 20;
    private static final int DIM_ANCHO = 20;

    private Tablero tablero;

    private boolean estadoDePartida;

    private Turno turno;


    public Partida(Jugador jugadorGuerreroZ,Jugador jugadorEnemigo) throws DimensionDeTableroInvalidoException, CasilleroOcupadoException {

        this.tablero = new Tablero(DIM_ANCHO,DIM_ALTO);
        this.tablero.initDeGuerrerosZ(jugadorGuerreroZ);
        this.tablero.initDeEnemigos(jugadorEnemigo);

        this.turno = new Turno(jugadorGuerreroZ,jugadorEnemigo);
        this.estadoDePartida = true;

    }
    public void pasar() {

        this.turno.pasar();
    }

    public Jugador turnoActual() {
        return this.turno.getJugadorActivo();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Personaje personajeEnPosicion(Posicion posicion) {
        return tablero.getCasillero(posicion).getPersonaje();
    }
}

