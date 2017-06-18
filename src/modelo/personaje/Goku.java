package modelo.personaje;

import modelo.acciones.Ataque;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.personaje.transformacion.goku.NormalGoku;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.tablero.Casillero;

import java.util.HashMap;

public class Goku extends Personaje {

	protected final int kiAtaqueEspecial = 20;
	private final double multMinVidaParaAyudaGohan = 0.3;

	public Goku(Casillero unCasillero) throws CasilleroOcupadoException{
		this.colocarEnCasillero(unCasillero);
		transformacion = new NormalGoku();
		vida = 500;
		VIDA_MAX = 500;
	}

	@Override
	protected void initAtaqueEspecialMap() {
		ataqueEspecialMap = new HashMap<>();
		ataqueEspecialMap.put(Piccolo.class, (x) -> ataqueEspecialAAmigo(x));
		ataqueEspecialMap.put(Gohan.class, (x) -> ataqueEspecialAAmigo(x));
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
		Ataque ataque = new Ataque(this, otroPersonaje, 1.50);
		ataque.execute();
		reducirKi(kiAtaqueEspecial);

	}

	public int getPoderDePelea() {
		if (this.vida < VIDA_MAX*0.3) {
			return (int)(transformacion.getPoderDePelea() * 1.2 );
		}
		return transformacion.getPoderDePelea();
	}


	public boolean necesitaAyudaDeGohan() {
		return this.getVida() < (VIDA_MAX*multMinVidaParaAyudaGohan);
	}
}
