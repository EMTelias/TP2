package modelo.personaje;

import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.tablero.Casillero;
import modelo.personaje.transformacion.majinboo.NormalMajinBoo;

import java.util.HashMap;

public class MajinBoo extends Personaje {

    protected final int kiAtaqueEspecial = 30;

    public MajinBoo(Casillero unCasillero) {
    	this.colocarEnCasillero(unCasillero);
        transformacion = new NormalMajinBoo();
        vida = 300;
        VIDA_MAX = 300;
    }

    @Override
    protected void initAtaqueEspecialMap() {
        ataqueEspecialMap = new HashMap<>();
        ataqueEspecialMap.put(Goku.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(Gohan.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(Piccolo.class, (x) -> ataqueEspecialAOponente(x));
        ataqueEspecialMap.put(Cell.class, (x) -> ataqueEspecialAAmigo(x));
        ataqueEspecialMap.put(Freezer.class, (x) -> ataqueEspecialAAmigo(x));
    }

    private void ataqueEspecialAAmigo(Personaje otroPersonaje) {
        throw new NoPuedeAtacarMismoEquipoException();
    }

    private void ataqueEspecialAOponente(Personaje otroPersonaje) {
        if (!tieneSuficienteKi(kiAtaqueEspecial)) {
            throw new KiInsuficienteException();
        }
        reducirKi(kiAtaqueEspecial);
        otroPersonaje.convertirseEnChocolate();

    }

}