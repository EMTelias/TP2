package controlador;


import modelo.excepciones.controlador.NoSePuedeSeleccionarMasDeDosPersonajesException;
import modelo.excepciones.personaje.NoEsSuTurnoException;
import modelo.personaje.Personaje;

import java.util.LinkedList;

public class PersonajeController {

    private final LinkedList<Personaje> colaPersonajes;

    public PersonajeController() {
        colaPersonajes = new LinkedList<>();
    }

    public void seleccionarPersonaje(Personaje unPersonaje){
        if (colaPersonajes.contains(unPersonaje)) {
            colaPersonajes.remove(unPersonaje);
            return;
        }

        if (colaPersonajes.size() == 2) {
            throw new NoSePuedeSeleccionarMasDeDosPersonajesException();
        }
        colaPersonajes.add(unPersonaje);
    }

    public Personaje obtenerPersonaje() {
        return colaPersonajes.removeFirst();
    }

    public void limpiar() {
        colaPersonajes.clear();
    }

    public Personaje verPrimerPersonaje() {
        return colaPersonajes.peekFirst();
    }

    public Personaje verSegundoPersonaje() {
        return colaPersonajes.peekLast();
    }
}
