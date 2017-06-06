package personaje;

import estado.goku.NormalGoku;
import excepciones.tablero.CasilleroOcupadoException;
import tablero.Casillero;

public class Goku extends Personaje {

	
	public Goku(Casillero unCasillero) throws CasilleroOcupadoException{
		unCasillero.colocar(this);
		casillero = unCasillero;
		
		
		
		estado = new NormalGoku();
		vida = 500;
	}

	
	
}
