package tablero;


import equipo.Equipo;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import personaje.*;

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

	public void initDeGuerrerosZ(Equipo guerrerosZ) throws CasilleroOcupadoException {
		//Creo los guerreros Z
		Posicion pos1 = new Posicion(1,1);
		Posicion pos2 = new Posicion(2,1);
		Posicion pos3 = new Posicion(3,1);

		Personaje goku = new Goku(this.getCasillero(pos1));
		Personaje gohan = new Gohan(this.getCasillero(pos2));
		Personaje piccolo = new Piccolo(this.getCasillero(pos3));

		goku.unirse(guerrerosZ);
		gohan.unirse(guerrerosZ);
		piccolo.unirse(guerrerosZ);
	}

	public void initDeEnemigos(Equipo enemigos) throws CasilleroOcupadoException {
		//Creo los enemigos
		Posicion pos1 = new Posicion(20,this.dimensionY);
		Posicion pos2 = new Posicion(20,this.dimensionY-1);
		Posicion pos3 = new Posicion(20,this.dimensionY-2);

		Personaje cell = new Cell(this.getCasillero(pos1));
		Personaje majinBoo = new MajinBoo(this.getCasillero(pos2));
		Personaje freezer = new Freezer(this.getCasillero(pos3));

		cell.unirse(enemigos);
		majinBoo.unirse(enemigos);
		freezer.unirse(enemigos);

	}
}
