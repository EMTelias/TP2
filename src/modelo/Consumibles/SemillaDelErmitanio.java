package modelo.Consumibles;

import modelo.personaje.Personaje;

public class SemillaDelErmitanio implements Consumible {

	static private int VIDA_REGENERADA = 100;
	
	@Override
	public void aplicarConsumibleA(Personaje unPersonaje) {
		unPersonaje.aumentarVida(VIDA_REGENERADA);
		
	}

}
