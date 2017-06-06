package tablero;


import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import personaje.Personaje;

import java.util.HashMap;
import java.util.List;

public class Tablero {

	private HashMap<Posicion,Casillero> tablero;
	private int dimensionX;
	private int dimensionY;
	
	
	public Tablero(int dimensionX, int dimensionY) throws DimensionDeTableroInvalidoException {
		if ((dimensionX <= 0) || (dimensionY <= 0) ) throw new DimensionDeTableroInvalidoException();
		tablero = new HashMap<Posicion,Casillero>();
				
		for (int i=1; i<=dimensionX; i++){
			for (int j=1; j<=dimensionY; j++){
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

	

	//La colocacion de los personajes sera distinto con las modificaciones que hay que hacer
	/* Agrego metodo para agregar los personajes al inicio de la partida */
	public void initDePersonajes(List<Personaje> guerrerosZ, List<Personaje> enemigos) throws CasilleroOcupadoException {
		int i = 1, j = this.dimensionY;
		for (Personaje personaje: guerrerosZ){
			Posicion pos = new Posicion(i,1);
			this.colocar(personaje,pos);
			i++;
		}
		for (Personaje personaje: enemigos){
			Posicion pos = new Posicion(20,j);
			this.colocar(personaje,pos);
			j--;
		}
	}
}
