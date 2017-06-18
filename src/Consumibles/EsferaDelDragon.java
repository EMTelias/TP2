package Consumibles;

public class EsferaDelDragon extends Efecto {

	@Override
	public int aplicarEfectoVelocidad(int velocidad) {
		return velocidad;
	}

	@Override
	public double aplicarEfectoAtaque(int ataque) {
		return ataque + 0.25;
	}
}