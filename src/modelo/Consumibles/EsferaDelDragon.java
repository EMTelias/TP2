package modelo.Consumibles;

public class EsferaDelDragon extends Efecto {

	private int CANTIDAD_TURNOS_ACTIVO = 2;
	private int cantidadDeTurnosActual = 0;
	
	@Override
	public int aplicarEfectoVelocidad(int velocidad) {
		return velocidad;
	}

	@Override
	public double aplicarEfectoAtaque(int ataque) {
		return ataque + 0.25;
	}

	@Override
	public Efecto pasarTurno() {
		if (cantidadDeTurnosActual == CANTIDAD_TURNOS_ACTIVO) 
			return new SinEfectoEspecial();
		else{
			cantidadDeTurnosActual = cantidadDeTurnosActual+1;
			return this;
		}
	}
}