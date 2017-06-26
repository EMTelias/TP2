package modelo.personaje.equipos;


import modelo.personaje.Personaje;

import java.util.ArrayList;

public class Equipo {
    private final String nombre;
    private ArrayList<Personaje> personajes;
    protected boolean suTurno;
    private int cantidadDeEsferas;


    public Equipo(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<Personaje>();
        cantidadDeEsferas = 0;
    }

    public void sumarEsfera(){
        cantidadDeEsferas++;
        System.out.println(cantidadDeEsferas);
    }

    public boolean tieneEsferasNecesariasParaGanar(){
        return (cantidadDeEsferas>=7);
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

    public boolean esSuTurno() {
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

    public void eliminarPersonaje(Personaje unPersonaje) {
        personajes.remove(unPersonaje);
    }

	public void pasarTurno() {
		for (Personaje unPersonaje : personajes) {
            unPersonaje.pasarTurno();
        }
        this.revisarTransformadosEnChocolate();
		
	}
}
