package modelo.personaje;

import modelo.acciones.Ataque;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.tablero.Casillero;
import modelo.personaje.transformacion.freezer.NormalFreezer;

import java.util.HashMap;

public class Freezer extends Personaje {

    protected final int kiAtaqueEspecial = 20;

    public Freezer(Casillero unCasillero) throws CasilleroOcupadoException {
    	this.colocarEnCasillero(unCasillero);
        transformacion = new NormalFreezer();
        vida = 400;
        VIDA_MAX = 400;
    }

    @Override
    protected void initAtaqueEspecialMap() {
        ataqueEspecialMap = new HashMap<>();
        ataqueEspecialMap.put(Goku.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(Gohan.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(Piccolo.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(Cell.class, (x) -> ataqueEspecialAAmigo(x));
        ataqueEspecialMap.put(MajinBoo.class, (x) -> ataqueEspecialAAmigo(x));
    }

    private void ataqueEspecialAAmigo(Personaje otroPersonaje) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        throw new NoPuedeAtacarMismoEquipoException();
    }

    private void ataqueEspecialAOponente(Personaje otroPersonaje) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        if (!tieneSuficienteKi(kiAtaqueEspecial)) {
            throw new KiInsuficienteException();
        }
        Ataque ataque = new Ataque(this, otroPersonaje, 1.50);
        ataque.execute();
        reducirKi(kiAtaqueEspecial);

    }

}