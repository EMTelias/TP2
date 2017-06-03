package tablero;

import java.util.Dictionary;

public class Tablero {

	private Dictionary<Posicion,Casillero> tablero;
	
	public Tablero(int dimensionX, int dimensionY) throws DimensionDeTableroInvalidoException{
		if (dimensionX <= 0 || dimensionY <= 0 ) throw new DimensionDeTableroInvalidoException();
		
		for (int i=0; i==dimensionX; i++){
			for (int j=0; j==dimensionY; j++){
				try {
					Posicion posicion = new Posicion(i,j);
					Casillero casillero = new Casillero(posicion);
					tablero.put(posicion, casillero); 				
				
				} catch (PosicionInvalidaException e){};
				
			}
		}
	}
	
	
	
}
