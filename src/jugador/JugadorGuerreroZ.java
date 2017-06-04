package jugador;

import personaje.Gohan;
import personaje.Goku;
import personaje.Piccolo;

public class JugadorGuerreroZ extends Jugador {

    public JugadorGuerreroZ(String nombreDeJugador){
        this.nombreDeJugador = nombreDeJugador;
        this.personajes.add(new Goku());
        this.personajes.add(new Gohan());
        this.personajes.add(new Piccolo());
    }
}
