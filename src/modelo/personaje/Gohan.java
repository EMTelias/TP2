package modelo.personaje;

import modelo.acciones.Ataque;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.tablero.Casillero;
import modelo.personaje.transformacion.gohan.NormalGohan;

import java.util.HashMap;

public class Gohan extends Personaje {

	protected final int kiAtaqueEspecial = 10;
	protected final int vidaMaxima = 300;
	protected final double multMinVidaParaAyudaPiccolo = 0.2;

    public Gohan(Casillero unCasillero) {
    	this.colocarEnCasillero(unCasillero);
        transformacion = new NormalGohan();
        vida = 300;
        VIDA_MAX = 300;
    }

    @Override
    protected void initAtaqueEspecialMap() {
        ataqueEspecialMap = new HashMap<>();
        ataqueEspecialMap.put(Goku.class, (x) -> ataqueEspecialAAmigo(x));
        ataqueEspecialMap.put(Piccolo.class, (x) -> ataqueEspecialAAmigo(x));
        ataqueEspecialMap.put(Cell.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(Freezer.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(MajinBoo.class, (x) -> ataqueEspecialAOponente(x));
    }

    private void ataqueEspecialAAmigo(Personaje otroPersonaje) {
        throw new NoPuedeAtacarMismoEquipoException();
    }

    private void ataqueEspecialAOponente(Personaje otroPersonaje) {
        if (!tieneSuficienteKi(kiAtaqueEspecial)) {
            throw new KiInsuficienteException();
        }
        Ataque ataque = new Ataque(this, otroPersonaje, 1.25);
        ataque.execute();
        reducirKi(kiAtaqueEspecial);
    }

    public boolean necesitaAyudaDePiccolo() {
        return this.getVida() < (vidaMaxima*multMinVidaParaAyudaPiccolo);
    }
}
