package tablero;

import direccion.Direccion;
import excepciones.direccion.NoHayDireccionPosibleException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import personaje.Personaje;

import java.util.HashMap;
import java.util.List;

public class Tablero {

	private HashMap<Posicion,Casillero> tablero;	
	
	
	public Tablero(int dimensionX, int dimensionY) throws DimensionDeTableroInvalidoException {
		if ((dimensionX <= 0) || (dimensionY <= 0) ) throw new DimensionDeTableroInvalidoException();
		tablero = new HashMap<Posicion,Casillero>();
				
		for (int i=1; i<=dimensionX; i++){
			for (int j=1; j<=dimensionY; j++){
					Posicion posicion = new Posicion(i,j);
					Casillero casillero = new Casillero(posicion,this);
					tablero.put(posicion, casillero); 					
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

	public boolean caminoDespejadoDesdeHasta(Posicion posicionInicial, Posicion posicionFinal) throws NoHayDireccionPosibleException {
		Direccion direccion = Direccion.getDireccion( posicionInicial,posicionFinal);
		boolean caminoDespejado = true;
		Posicion posicion = posicionInicial;
		
		while ( (caminoDespejado) && (!posicion.equals(posicionFinal))) {
			posicion = posicion.siguientePosicionEnDireccion(direccion);
			caminoDespejado = caminoDespejado && tablero.get(posicion).estaVacio();
		}
		return caminoDespejado;
	}
	
	/* Agrego metodo para agregar los personajes al inicio de la partida */
	public void initDePersonajes(List<Personaje> guerrerosZ, List<Personaje> enemigos) throws CasilleroOcupadoException {
		int i = 1, j = 1;
		for (Personaje personaje: guerrerosZ){
			Posicion pos = new Posicion(i,1);
			this.colocar(personaje,pos);
			i++;
		}
		for (Personaje personaje: guerrerosZ){
			Posicion pos = new Posicion(j,10);
			this.colocar(personaje,pos);
			j++;
		}
	}
}
