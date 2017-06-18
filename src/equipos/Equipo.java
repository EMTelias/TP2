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

    public String getNombre() {
        return this.nombre;
    }

    public void revisarTransformadosEnChocolate() {
        for (Personaje unPersonaje : personajes) {
            unPersonaje.revisarTransformacionChocolate();
        }
    }

    public void aumentarKi(int aumento) {
        for (Personaje unPersonaje : personajes) {
            unPersonaje.aumentarKi(aumento);
        }
    }

    public void eliminarPersonaje(Personaje unPersonaje) {
        personajes.remove(unPersonaje);
    }
}
