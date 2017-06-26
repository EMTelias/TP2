package modelo.Consumibles;

import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.personaje.Personaje;
import modelo.tablero.Casillero;

public class EsferaDelDragon extends Efecto {

	private int CANTIDAD_TURNOS_ACTIVO = 2;
	private int cantidadDeTurnosActual = 1;
	private Casillero casillero;
	
	
	public EsferaDelDragon(Casillero unCasillero) throws CasilleroOcupadoException {
		unCasillero.colocar(this);
		casillero = unCasillero;
	}

	public EsferaDelDragon() {} // solo para los test iniciales

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

	@Override
	public void aplicarConsumibleA(Personaje unPersonaje) {
		this.aplicarEfectoA(unPersonaje);
        unPersonaje.sumarEsferaASuEquipo();
	}
}