package modelo.Consumibles;

import java.util.Random;

import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

public class GeneradorDeConsumiblesRandom implements GeneradorDeConsumibles {

	@Override
	public void generarConsumibleEn(Tablero tablero, int dimensionMaximaX, int dimensionMaximaY) {
		Random unRandom = new Random();
		
		if (unRandom.nextDouble() > 0.25 ) return; // 25 porciento de chance que salga un consumible en un turno
		
		switch ((int)(unRandom.nextDouble() * 2 + 0)){
			case 0:
		        this.colocarNubeVoladora(tablero,dimensionMaximaX,dimensionMaximaY,unRandom);
				break;
		 
		    case 1:
		    	this.colocarSemillaDelErmitanio(tablero,dimensionMaximaX,dimensionMaximaY,unRandom);
		    	break;
		
		    case 2:
		    	this.colocarEsferaDelDragon(tablero,dimensionMaximaX,dimensionMaximaY,unRandom);
		        break;
		}
	}

	private void colocarNubeVoladora(Tablero tablero, int dimensionMaximaX, int dimensionMaximaY, Random unRandom){
		Boolean pudoColocar = false;
		while (!pudoColocar){
			Posicion posicionAColocar = this.generarPosicionRandom(dimensionMaximaX,dimensionMaximaY, unRandom);
			Casillero casilleroAColocar = tablero.getCasillero(posicionAColocar);
			try {
				new NubeVoladora(casilleroAColocar);
				pudoColocar = true;
			} catch (CasilleroOcupadoException e) {
				pudoColocar = false;
			}
		}		
	}
	
	private void colocarSemillaDelErmitanio(Tablero tablero, int dimensionMaximaX, int dimensionMaximaY, Random unRandom){
		Boolean pudoColocar = false;
		while (!pudoColocar){
			Posicion posicionAColocar = this.generarPosicionRandom(dimensionMaximaX,dimensionMaximaY, unRandom);
			Casillero casilleroAColocar = tablero.getCasillero(posicionAColocar);
			try {
				new SemillaDelErmitanio(casilleroAColocar);
				pudoColocar = true;
			} catch (CasilleroOcupadoException e) {
				pudoColocar = false;
			}
		}		
	}
	
	private void colocarEsferaDelDragon(Tablero tablero, int dimensionMaximaX, int dimensionMaximaY, Random unRandom){
		Boolean pudoColocar = false;
		while (!pudoColocar){
			Posicion posicionAColocar = this.generarPosicionRandom(dimensionMaximaX,dimensionMaximaY, unRandom);
			Casillero casilleroAColocar = tablero.getCasillero(posicionAColocar);
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