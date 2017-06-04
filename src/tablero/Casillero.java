package tablero;

import excepciones.direccion.NoHayDireccionPosibleException;
import excepciones.tablero.CasilleroOcupadoException;
import personaje.Personaje;

public class Casillero {

	
	private Personaje personaje;
	private Posicion posicion;
	private Tablero tablero;
	
	public Casillero(Posicion unaPosicion,Tablero unTablero) {
		posicion = unaPosicion;
		personaje = null;
		tablero = unTablero;
	}
	
	//constructor para tests:
	public Casillero(Posicion unaPosicion){
		posicion = unaPosicion;
		personaje = null;
	}

	public boolean estaVacio() {
		return ( personaje == null );
	}

	public void colocar(Personaje unPersonaje) throws CasilleroOcupadoException {
		if (personaje != null) throw new CasilleroOcupadoException();
		personaje = unPersonaje;
		personaje.ubicarEn(this);
	}

	public void quitar() {
		personaje = null;
	}

	public int distanciaHasta(Casillero otroCasillero) {
		return posicion.distanciaHasta(otroCasillero.posicion);
	}

	public boolean caminoDespejadoHasta(Casillero otroCasillero) throws NoHayDireccionPosibleException {
		return tablero.caminoDespejadoDesdeHasta(posicion, otroCasillero.posicion);
	}

}
