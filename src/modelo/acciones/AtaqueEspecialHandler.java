package modelo.acciones;

import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.personaje.Personaje;

public interface AtaqueEspecialHandler {
    void ataqueEspecialA(Personaje otroPersonaje) ;
}
