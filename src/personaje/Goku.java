package personaje;

import acciones.Ataque;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.transformacion.KiInsuficienteException;
import transformacion.goku.NormalGoku;
import excepciones.tablero.CasilleroOcupadoException;
import tablero.Casillero;

import java.util.HashMap;

public class Goku extends Personaje {

	protected final int kiAtaqueEspecial = 20;
	protected final int vidaMaxima = 500;
	private final double multMinVidaParaAyudaGohan = 0.3;

	public Goku(Casillero unCasillero) throws CasilleroOcupadoException{
		unCasillero.colocar(this);
		casillero = unCasillero;
		transformacion = new NormalGoku();
		vida = vidaMaxima;
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
		if (this.vida < vidaMaxima*0.3) {
			return (int)(transformacion.getPoderDePelea() * 1.2 );
		}
		return transformacion.getPoderDePelea();
	}


	public boolean necesitaAyudaDeGohan() {
		return this.getVida() < (vidaMaxima*multMinVidaParaAyudaGohan);
	}
}
