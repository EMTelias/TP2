package jugador;

import personaje.Personaje;

import java.util.ArrayList;
import java.util.List;

public abstract class Jugador {

    protected String nombreDeJugador;
    protected boolean suTurno;
    protected List<Personaje> personajes = new ArrayList<>();

    public String getNombre(){ return nombreDeJugador; }


    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setTurno(boolean esSuTurno) {
        this.suTurno = esSuTurno;
    }

    public boolean getTurno() {
        return this.suTurno;
    }

    public void agregarPersonaje(Personaje unPersonaje){
        personajes.add(unPersonaje);
    }
}
