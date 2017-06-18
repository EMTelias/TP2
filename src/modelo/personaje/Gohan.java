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

    public Gohan(Casillero unCasillero) throws CasilleroOcupadoException{
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

    private void ataqueEspecialAAmigo(Personaje otroPersonaje) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        throw new NoPuedeAtacarMismoEquipoException();
    }

    private void ataqueEspecialAOponente(Personaje otroPersonaje) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        if (!tieneSuficienteKi(kiAtaqueEspecial)) {
            throw new KiInsuficienteException();
        }
        Ataque ataque = new Ataque(this, otroPersonaje, 1.25);
        ataque.execute();
        reducirKi(kiAtaqueEspecial);
    }
    /*public void transformar(EstadoGuerrerosZ estado) throws NoPuedeTransformarException, NoHayProximaTransformacionException, KiInsuficienteException {
        if (estado.puedeTransformar(this)){
            this.modelo.personaje.transformacion = modelo.personaje.transformacion.transformar(this);
        }else{
            throw new NoPuedeTransformarException();
        }
    }
    public boolean primeraTransformacion(){
        if (this.modelo.personaje.transformacion.getClass() == NormalGohan.class){ return true; }
        else { return false; }
    }*/

    public boolean necesitaAyudaDePiccolo() {
        return this.getVida() < (vidaMaxima*multMinVidaParaAyudaPiccolo);
    }
}
