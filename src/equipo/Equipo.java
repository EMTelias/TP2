package equipo;


import personaje.Personaje;

import java.util.ArrayList;

public class Equipo {
    private final String nombre;
    private ArrayList<Personaje> personajes;

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
}
