package modelo.tablero;

import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.personaje.Personaje;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Camino {

	List<Casillero> casillerosDelCamino;
	
	public Camino(List<Casillero> casilleros) {
		if (casilleros.isEmpty()) throw new CaminoInvalidoException();
		casillerosDelCamino = new ArrayList<Casillero>();
		casillerosDelCamino.addAll(casilleros);
	}

	public void recorrerCon(Personaje unPersonaje) {
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

	public void siDistanciaEsMayor(int velocidad, Class<NoPuedeMoverAEsaDistanciaException> excepcion) throws InstantiationException, IllegalAccessException{
		if (casillerosDelCamino.size() > velocidad ) throw excepcion.newInstance();
	}

	public Casillero getPrimerCasillero() {
		return casillerosDelCamino.get(0);
	}
}
