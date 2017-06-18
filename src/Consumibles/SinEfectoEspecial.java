package Consumibles;

public class SinEfectoEspecial extends Efecto {

	@Override
	public int aplicarEfectoVelocidad( int velocidad ) {
		return velocidad;
	}

	@Override
	public double aplicarEfectoAtaque(int ataque) {
		return ataque;
	}

}
