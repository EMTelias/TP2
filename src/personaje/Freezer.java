package personaje;

import acciones.Ataque;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.transformacion.KiInsuficienteException;
import tablero.Casillero;
import transformacion.freezer.NormalFreezer;

import java.util.HashMap;

public class Freezer extends Personaje {

    protected final int kiAtaqueEspecial = 20;

    public Freezer(Casillero unCasillero) throws CasilleroOcupadoException {
        unCasillero.colocar(this);
        casillero = unCasillero;
        transformacion = new NormalFreezer();
        vida = 400;
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