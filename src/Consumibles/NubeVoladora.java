package Consumibles;

public class NubeVoladora extends Efecto {

	@Override
	public int aplicarEfectoVelocidad(int velocidad) {
		return velocidad*2;
	}

	@Override
	public double aplicarEfectoAtaque(int ataque) {
		return ataque;
	}

}
