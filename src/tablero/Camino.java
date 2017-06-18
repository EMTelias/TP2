package tablero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;
import excepciones.tablero.CasilleroOcupadoException;
import personaje.Personaje;

public class Camino {

	List<Casillero> casillerosDelCamino;
	
	public Camino(List<Casillero> casilleros) throws CaminoInvalidoException{
		if (casilleros.isEmpty()) throw new CaminoInvalidoException();
		casillerosDelCamino = new ArrayList<Casillero>();
		casillerosDelCamino.addAll(casilleros);
	}

	public void recorrerCon(Personaje unPersonaje) throws NoPuedeMoverCaminoObstruidoException {
		Casillero casilleroOrigen = unPersonaje.getCasillero();
		Iterator<Casillero> iterator = casillerosDelCamino.iterator();
		while (iterator.hasNext()){
			Casillero unCasillero = iterator.next();
			try{
				unPersonaje.sacarDeSuCasillero();
				unPersonaje.colocarEnCasillero(unCasillero);
			}catch(CasilleroOcupadoException e){
				try {
					unPersonaje.colocarEnCasillero(casilleroOrigen);
				} catch (CasilleroOcupadoException e1) {
					//Era su casillero origen, nunca lanza esta excepcion
				}
				throw new NoPuedeMoverCaminoObstruidoException();
			}
		}
	}

	public void siDistanciaEsMayor(int velocidad, Class<NoPuedeMoverAEsaDistanciaException> excepcion) throws InstantiationException, IllegalAccessException, NoPuedeMoverAEsaDistanciaException {
		if (casillerosDelCamino.size() > velocidad ) throw excepcion.newInstance();
	}
	
}
