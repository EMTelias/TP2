package modelo.Consumibles;

import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.tablero.Casillero;

public class NubeVoladora extends Efecto {

	private int CANTIDAD_TURNOS_ACTIVO = 2;
	private int cantidadDeTurnosActual = 0;
	private Casillero casillero;
	
	public NubeVoladora(Casillero unCasillero) throws CasilleroOcupadoException {
		unCasillero.colocar(this);
		casillero = unCasillero;
	}
	
	public NubeVoladora() {}// solo para test iniciales

	@Override
	public int aplicarEfectoVelocidad(int velocidad) {
		return velocidad*2;
	}

	@Override
	public double aplicarEfectoAtaque(int ataque) {
		return ataque;
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
