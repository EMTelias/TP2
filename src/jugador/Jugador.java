package jugador;

import personaje.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Jugador {

    protected String nombreDeJugador;
    protected List<Personaje> personajes = new ArrayList<>();

    public String getNombre(){ return nombreDeJugador; }


    public List<Personaje> getPersonajes() {
        return personajes;
    }
}
