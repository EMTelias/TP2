package tablero;

import excepciones.tablero.CasilleroOcupadoException;
import org.junit.Assert;
import org.junit.Test;
import personaje.Gohan;
import personaje.Goku;
import personaje.Personaje;

public class CasilleroTest {

	@Test
	public void testAlCrearCasilleroEstaVacio(){
		Posicion posicion = new Posicion(2,3);
		Casillero casillero = new Casillero(posicion);
		Assert.assertTrue(casillero.estaVacio());	
	}

	@Test
	public void testCreoAGokuEnUnCasilleroEstaVacioDevuelveFalse() throws CasilleroOcupadoException{
		Casillero casillero = new Casillero(new Posicion(1,1));
		Personaje goku = new Goku(casillero);
		Assert.assertFalse(casillero.estaVacio());
	}
	
	@Test(expected = CasilleroOcupadoException.class)
	public void testSeCreanGokuYGohanEnElMismoCasilleroDevuelveCasilleroOcupadoException() throws CasilleroOcupadoException{
		Casillero casillero = new Casillero(new Posicion(1,1));
		Personaje goku = new Goku(casillero);
		Personaje gohan = new Gohan(casillero);
	}
	

}
