package equipos;


import personaje.Personaje;

import java.util.ArrayList;

public class Equipo {
    private final String nombre;
    private ArrayList<Personaje> personajes;
    protected boolean suTurno;


    public Equipo(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<Personaje>();
    }
    
    public void agregarPersonaje(Personaje unPersonaje) {
        this.personajes.add(unPersonaje);
    }

    public boolean esMiembro(Personaje otroPersonaje) {
        return this.personajes.contains(otroPersonaje);
    }

    public ArrayList<Personaje> getPersonajes() {
        return this.personajes;
    }

    public void setTurno(boolean esSuTurno) {
        this.suTurno = esSuTurno;
    }

    public boolean getTurno() {
        return this.suTurno;
    }

    public void revisarTransformadosEnChocolate() {
        for (Personaje unPersonaje : personajes) {
            unPersonaje.revisarTransformacionChocolate();
        }
    }
}
