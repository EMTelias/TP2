package tablero;

import java.util.HashMap;

import personaje.Personaje;

public class Tablero {

	private HashMap<Posicion,Casillero> tablero;	
	
	
	public Tablero(int dimensionX, int dimensionY) throws DimensionDeTableroInvalidoException{
		if ((dimensionX <= 0) || (dimensionY <= 0) ) throw new DimensionDeTableroInvalidoException();
		tablero = new HashMap<Posicion,Casillero>();
				
		for (int i=1; i<=dimensionX; i++){
			for (int j=1; j<=dimensionY; j++){
				try {
					Posicion posicion = new Posicion(i,j);
					Casillero casillero = new Casillero(posicion);
					tablero.put(posicion, casillero); 				
					
				} catch (PosicionInvalidaException e){};	
			}
		}		
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
