package tablero;

import personaje.Personaje;

public class Casillero {

	private Posicion posicion;
	private Personaje personaje;
	
	
	public Casillero(Posicion unaPosicion) {
		posicion = unaPosicion;
		personaje = null;
	}

	public boolean estaVacio() {
		return ( personaje==null );
	}

	public void colocar(Personaje unPersonaje) throws CasilleroOcupadoException {
		if (personaje != null) throw new CasilleroOcupadoException();
		personaje = unPersonaje;
		
	}

}
