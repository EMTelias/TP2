package acciones;

import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.transformacion.KiInsuficienteException;
import personaje.Personaje;

public interface AtaqueEspecialHandler {
    void ataqueEspecialA(Personaje otroPersonaje) throws NoPuedeAtacarAEsaDistanciaException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException;
}
