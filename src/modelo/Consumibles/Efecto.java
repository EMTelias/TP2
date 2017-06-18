package modelo.Consumibles;

import modelo.personaje.Personaje;

public abstract class Efecto implements Consumible{

	@Override
	public void aplicarConsumibleA(Personaje unPersonaje) {
		this.aplicarEfectoA(unPersonaje);
	}

	public void aplicarEfectoA(Personaje unPersonaje){
		unPersonaje.aplicarEfecto(this);
	}

	public abstract int aplicarEfectoVelocidad(int velocidad);
	public abstract double aplicarEfectoAtaque(int ataque);

	public abstract Efecto pasarTurno();
}
