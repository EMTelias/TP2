package modelo.acciones;

import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;

public interface Command {
    void execute() throws NoPuedeAtacarAEsaDistanciaException;
}
