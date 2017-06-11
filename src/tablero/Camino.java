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
		if (casilleros.isEmpty()) throw new CaminoInvalidoException();
		casillerosDelCamino = new ArrayList<Casillero>();
		casillerosDelCamino.addAll(casilleros);
	}

	public int distancia() {
		return casillerosDelCamino.size();
	}

	public Casillero recorrerCon(Personaje unPersonaje) throws NoPuedeMoverCaminoObstruidoException {
		
		Casillero casilleroDestino = this.getCasilleroDestino();
		
		if (this.puedeMover()){
			try {
				casilleroDestino.colocar(unPersonaje);
			}catch (CasilleroOcupadoException e) {
				// si puedeMover es true => nunca lanza CasilleroOcupadoException
			}
		}else{
			throw new NoPuedeMoverCaminoObstruidoException();
		}
		
		return casilleroDestino;
	} 
		
	private boolean puedeMover(){
		boolean puedeMover = true;
		
		Iterator<Casillero> iterator = casillerosDelCamino.iterator();
		
		while (iterator.hasNext()){
			Casillero unCasillero = iterator.next();
			puedeMover = puedeMover && unCasillero.estaVacio();
		}
		
		return puedeMover;
	}
	
	private Casillero getCasilleroDestino(){
		Iterator<Casillero> iterator = casillerosDelCamino.iterator();
		Casillero casilleroDestino =  iterator.next();	
		while (iterator.hasNext()){
			casilleroDestino = iterator.next();
		}
		return casilleroDestino;
	}

}
