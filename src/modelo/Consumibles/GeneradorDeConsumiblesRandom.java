package modelo.Consumibles;

import java.util.Random;

import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

public class GeneradorDeConsumiblesRandom implements GeneradorDeConsumibles {

	@Override
	public Posicion generarConsumibleEn(Tablero tablero, int dimensionMaximaX, int dimensionMaximaY) {
		Random unRandom = new Random();
		
		if (unRandom.nextDouble() > 0.25 ) return null; // 25 porciento de chance que salga un consumible en un turno
		Posicion posicionAColocar = this.generarPosicionRandom(dimensionMaximaX,dimensionMaximaY, unRandom);


		switch ((int)(unRandom.nextDouble() * 2 + 0)){
			case 0:
		        this.colocarNubeVoladora(tablero,posicionAColocar);
				break;
		 
		    case 1:
		    	this.colocarSemillaDelErmitanio(tablero,posicionAColocar);
		    	break;
		
		    case 2:
		    	this.colocarEsferaDelDragon(tablero,posicionAColocar);
		        break;
		}

		return posicionAColocar;
	}

	private void colocarNubeVoladora(Tablero tablero, Posicion unaPosicion){
		Boolean pudoColocar = false;
		while (!pudoColocar){
			Casillero casilleroAColocar = tablero.getCasillero(unaPosicion);
			try {
				new NubeVoladora(casilleroAColocar);
				pudoColocar = true;
			} catch (CasilleroOcupadoException e) {
				pudoColocar = false;
			}
		}		
	}
	
	private void colocarSemillaDelErmitanio(Tablero tablero, Posicion unaPosicion){
		Boolean pudoColocar = false;
		while (!pudoColocar){
			Casillero casilleroAColocar = tablero.getCasillero(unaPosicion);
			try {
				new SemillaDelErmitanio(casilleroAColocar);
				pudoColocar = true;
			} catch (CasilleroOcupadoException e) {
				pudoColocar = false;
			}
		}		
	}
	
	private void colocarEsferaDelDragon(Tablero tablero, Posicion unaPosicion){
		Boolean pudoColocar = false;
		while (!pudoColocar){
			Casillero casilleroAColocar = tablero.getCasillero(unaPosicion);
			try {
				new EsferaDelDragon(casilleroAColocar);
				pudoColocar = true;
			} catch (CasilleroOcupadoException e) {
				pudoColocar = false;
			}
		}		
	}
	
	private Posicion generarPosicionRandom(int dimensionMaximaX, int dimensionMaximaY, Random unRandom){
		int posicionRandomX = (int)(unRandom.nextDouble() * dimensionMaximaX + 0);
		int posicionRandomY = (int)(unRandom.nextDouble() * dimensionMaximaY + 0);			
		return new Posicion(posicionRandomX,posicionRandomY);
	}
}