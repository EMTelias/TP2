package modelo.Consumibles;

import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

public interface GeneradorDeConsumibles {

	public Posicion generarConsumibleEn(Tablero tablero, int dimensionMaximaX, int dimensionMaximaY);


}
