package modelo.Consumibles;

import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

public class GeneradorDeConsumiblesSinActividad implements GeneradorDeConsumibles {

	@Override
	public Posicion generarConsumibleEn(Tablero tablero, int dimensionMaximaX, int dimensionMaximaY) {
		// no hace nada, es para no tener random en los test
		return null;
	}

}
