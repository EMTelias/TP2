package personaje;

import transformacion.goku.NormalGoku;
import excepciones.tablero.CasilleroOcupadoException;
import tablero.Casillero;

public class Goku extends Personaje {

	
	public Goku(Casillero unCasillero) throws CasilleroOcupadoException{
		unCasillero.colocar(this);
		casillero = unCasillero;
		
		
		
		transformacion = new NormalGoku();
		vida = 500;
	}

	
	
}
