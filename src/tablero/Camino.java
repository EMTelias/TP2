package tablero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;
import excepciones.tablero.CasilleroOcupadoException;
import personaje.Personaje;

public class Camino {

	List<Casillero> casillerosDelCamino;
	
	public Camino(List<Casillero> casilleros) throws CaminoInvalidoException{
		if (  (casilleros.isEmpty())  ||  (casilleros.size() == 1) ){
			throw new CaminoInvalidoException();
		}
		casillerosDelCamino = new ArrayList<Casillero>();
		casillerosDelCamino.addAll(casilleros);
	}

	public int distancia() {
		return casillerosDelCamino.size()-1;
	}

	public void recorrer() throws NoPuedeMoverCaminoObstruidoException {
		boolean puedeMover = true;
		
		Iterator<Casillero> iterator = casillerosDelCamino.iterator();
		Casillero unCasillero = iterator.next();
		Casillero casilleroOrigen = unCasillero;
		
		while (iterator.hasNext()){
			unCasillero = iterator.next();
			puedeMover = puedeMover && unCasillero.estaVacio();
		}
		
		if (puedeMover){
			Casillero casilleroDestino = unCasillero;
			Personaje personajeQueRecorre = casilleroOrigen.getPersonaje();
			
			casilleroOrigen.vaciar();
			try {
				casilleroDestino.colocar(personajeQueRecorre);
				} catch (CasilleroOcupadoException e) {
				// si puedeMover es true => nunca lanza CasilleroOcupadoException
				}
			}
		else{
			throw new NoPuedeMoverCaminoObstruidoException();
		}
	} 
		
}
