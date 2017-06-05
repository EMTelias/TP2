package partida;

import jugador.Jugador;

public class Turno {
    private Jugador jugadorEnMovimiento;
    private Jugador jugadorEnEspera;

    public Turno(Jugador jugador1,Jugador jugador2){
        this.jugadorEnMovimiento = jugador1;
        this.jugadorEnEspera = jugador2;
        jugador1.setTurno(true);
        jugador2.setTurno(false);
    }
    public Jugador getJugadorActivo(){
        return this.jugadorEnMovimiento;
    }

    public void pasar() {
        Jugador jugador = this.jugadorEnMovimiento;
        this.jugadorEnMovimiento = this.jugadorEnEspera;
        this.jugadorEnEspera = jugador;
        this.jugadorEnMovimiento.setTurno(true);
        this.jugadorEnEspera.setTurno(false);
    }
}
