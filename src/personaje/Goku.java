package personaje;

import estado.EstadoGoku;
import estado.NormalGoku;
import tablero.Posicion;

public class Goku extends Personaje {

	
	public Goku(){
		estado = new NormalGoku();
	}

	
	
}
