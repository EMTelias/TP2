/*package equipo;

import excepciones.tablero.CasilleroOcupadoException;
import personaje.Gohan;
import personaje.Goku;
import personaje.Piccolo;

public class EstadoGuerrerosZ {
    private static final int MAX_VIDA_GOKU = 500;
    private static final int MAX_VIDA_PICC = 500;
    private static final int MAX_VIDA_GOHA = 300;
    private static final double MULT_MIN_GOHA = 0.3;
    private static final double MULT_MIN_PICC = 0.25;
    private Goku goku;
    private Piccolo piccolo;
    private Gohan gohan;

    public EstadoGuerrerosZ(Goku goku, Gohan gohan, Piccolo piccolo) throws CasilleroOcupadoException {
        this.goku = goku;
        this.piccolo = piccolo;
        this.gohan = gohan;
    }
    public boolean puedeTransformar(Gohan gohan){
        if (gohan.primeraTransformacion()){ return true; }
        if (this.goku.getVida() < MAX_VIDA_GOKU*MULT_MIN_GOHA || this.piccolo.getVida() < MAX_VIDA_PICC*MULT_MIN_GOHA){ return true; }
        else{ return false; }
    }
    public boolean puedeTransformar(Piccolo piccolo){
        if (piccolo.primeraTransformacion()){ return true; }
        if (this.gohan.getVida() < MAX_VIDA_GOHA*MULT_MIN_PICC){ return true; }
        else{ return false; }
    }

}*/
