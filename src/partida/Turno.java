package partida;

import jugador.Jugador;

public class Turno {
    private Jugador jugadorEnMovimiento;
    private Jugador jugadorEnEspera;

    public Turno(Jugador jugador1,Jugador jugador2){
        this.jugadorEnMovimiento = jugador1;
        this.jugadorEnEspera = jugador2;
    }
    public Jugador getJugadorActivo(){
        Jugador jugador = jugadorEnMovimiento;
        jugadorEnMovimiento = jugadorEnEspera;
        jugadorEnEspera = jugador;
        return jugador;
    }
}
