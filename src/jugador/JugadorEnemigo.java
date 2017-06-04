package jugador;

import personaje.Cell;
import personaje.Freezer;
import personaje.MajinBoo;

/**
 * Created by EMT on 4/6/2017.
 */
public class JugadorEnemigo extends Jugador{

    public JugadorEnemigo(String nombreDeJugador){
        this.nombreDeJugador = nombreDeJugador;
        this.personajes.add(new Freezer());
        this.personajes.add(new Cell());
        this.personajes.add(new MajinBoo());
    }
}
