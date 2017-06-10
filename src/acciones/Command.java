package acciones;

import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;

public interface Command {
    void execute() throws NoPuedeAtacarAEsaDistanciaException;
}
