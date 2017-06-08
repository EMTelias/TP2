package personaje;

import transformacion.gohan.NormalGohan;
import excepciones.tablero.CasilleroOcupadoException;
import tablero.Casillero;

public class Gohan extends Personaje {

	
    public Gohan(Casillero unCasillero) throws CasilleroOcupadoException{
    	unCasillero.colocar(this);
    	casillero = unCasillero;
    	
        transformacion = new NormalGohan();
        vida = 300;
    }

}
