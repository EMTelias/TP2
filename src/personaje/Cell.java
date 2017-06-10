package personaje;

import acciones.Ataque;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.transformacion.KiInsuficienteException;
import tablero.Casillero;
import transformacion.cell.NormalCell;

import java.util.HashMap;

public class Cell extends Personaje {

    protected int absorber = 0;
    protected final int kiAtaqueEspecial = 5;

    public Cell(Casillero unCasillero) throws CasilleroOcupadoException {
        unCasillero.colocar(this);
        casillero = unCasillero;
        transformacion = new NormalCell();
        vida = 500;
    }

    @Override
    protected void initAtaqueEspecialMap() {
        ataqueEspecialMap = new HashMap<>();
        ataqueEspecialMap.put(Goku.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(Gohan.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(Piccolo.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(Freezer.class, (x) -> ataqueEspecialAAmigo(x));
        ataqueEspecialMap.put(MajinBoo.class, (x) -> ataqueEspecialAAmigo(x));
    }

    private void ataqueEspecialAAmigo(Personaje otroPersonaje) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        throw new NoPuedeAtacarMismoEquipoException();
    }

    private void ataqueEspecialAOponente(Personaje otroPersonaje) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        if (!tieneSuficienteKi(kiAtaqueEspecial)) {
            throw new KiInsuficienteException();
        }
        int vidaAntesAtaque = otroPersonaje.getVida();
        Ataque ataque = new Ataque(this, otroPersonaje, 1);
        ataque.execute();
        reducirKi(kiAtaqueEspecial);
        int vidaDespuesAtaque = otroPersonaje.getVida();
        this.absorber(vidaAntesAtaque - vidaDespuesAtaque);
    }

    private void absorber(int danioProducido) {
        this.absorber += 1;
        this.aumentarVida(danioProducido);
    }

    public int getAbsorber() {
        return this.absorber;
    }



}