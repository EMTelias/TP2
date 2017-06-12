package tablero;


import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import jugador.Jugador;
import jugador.JugadorEnemigo;
import jugador.JugadorGuerreroZ;
import personaje.*;

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
	/*public void initDePersonajes(List<Personaje> guerrerosZ, List<Personaje> enemigos) throws CasilleroOcupadoException {
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
		}*/

	public void initDeGuerrerosZ(Jugador guerreroZ) throws CasilleroOcupadoException {
		//Creo los guerreros Z
		Posicion pos1 = new Posicion(1,1);
		Posicion pos2 = new Posicion(2,1);
		Posicion pos3 = new Posicion(3,1);

		Personaje goku = new Goku(this.getCasillero(pos1));
		Personaje gohan = new Gohan(this.getCasillero(pos2));
		Personaje piccolo = new Piccolo(this.getCasillero(pos3));

		//this.colocar(goku,pos1);
		//this.colocar(gohan,pos2);
		//this.colocar(piccolo,pos3);

		guerreroZ.agregarPersonaje(goku);
		guerreroZ.agregarPersonaje(gohan);
		guerreroZ.agregarPersonaje(piccolo);

	}

	public void initDeEnemigos(Jugador enemigo) throws CasilleroOcupadoException {
		//Creo los enemigos
		Posicion pos1 = new Posicion(20,this.dimensionY);
		Posicion pos2 = new Posicion(20,this.dimensionY-1);
		Posicion pos3 = new Posicion(20,this.dimensionY-2);

		Personaje cell = new Cell(this.getCasillero(pos1));
		Personaje majinBoo = new MajinBoo(this.getCasillero(pos2));
		Personaje freezer = new Freezer(this.getCasillero(pos3));

		//this.colocar(cell,pos1);
		//this.colocar(majinBoo,pos2);
		//this.colocar(freezer,pos3);

		enemigo.agregarPersonaje(cell);
		enemigo.agregarPersonaje(majinBoo);
		enemigo.agregarPersonaje(freezer);

	}
}
