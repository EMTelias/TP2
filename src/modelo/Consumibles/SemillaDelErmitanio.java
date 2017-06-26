package modelo.Consumibles;

import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.personaje.Personaje;
import modelo.tablero.Casillero;

public class SemillaDelErmitanio implements Consumible {

	static private int VIDA_REGENERADA = 100;
	private Casillero casillero;
	
	public SemillaDelErmitanio(Casillero unCasillero) {
		unCasillero.colocar(this);
		casillero = unCasillero;
	}

	public SemillaDelErmitanio() {// solo para test iniciales
	
	}

	@Override
	public void aplicarConsumibleA(Personaje unPersonaje) {
		unPersonaje.aumentarVida(VIDA_REGENERADA);
		
	}

}
