package tablero;


import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import personaje.Personaje;

import java.util.HashMap;

public class Tablero {

	private HashMap<Posicion,Casillero> tablero;
	private int dimensionX;
	private int dimensionY;
	
	
	public Tablero(int dimensionX, int dimensionY) throws DimensionDeTableroInvalidoException {
		if ((dimensionX <= 0) || (dimensionY <= 0) ) throw new DimensionDeTableroInvalidoException();
		tablero = new HashMap<Posicion,Casillero>();
				
		for (int i=0; i<=dimensionX; i++){
			for (int j=0; j<=dimensionY; j++){
					Posicion posicion = new Posicion(i,j);
					Casillero casillero = new Casillero(posicion);
					tablero.put(posicion, casillero); 					
			}
		}
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
	}

	public boolean estaVacioEn(Posicion posicion) {
		Casillero casillero = tablero.get(posicion);
		return casillero.estaVacio();
	}

	public void colocar(Personaje goku, Posicion posicion) throws CasilleroOcupadoException {
		Casillero casillero = tablero.get(posicion);
		casillero.colocar(goku);
	}

	public Casillero getCasillero(Posicion posicion) {
		return tablero.get(posicion);
	}


}
