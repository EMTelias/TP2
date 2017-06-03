package tablero;

import org.junit.Assert;
import org.junit.Test;

import tablero.Posicion;
import tablero.PosicionInvalidaException;

public class PosicionTest {

	@Test(expected=PosicionInvalidaException.class)
	public void testCreoPosicionConValorNegativoEnXDevuelvePosicionInvalidaException() throws PosicionInvalidaException{
		new Posicion(-2,3);
	}
	
	@Test(expected=PosicionInvalidaException.class)
	public void testCreoPosicionConValorNegativoEnYDevuelvePosicionInvalidaException() throws PosicionInvalidaException{
		new Posicion(3,-2);
	}
	
	@Test
	public void testComparoDosPosicionesIgualesLaComparacionDevuelveTrue() throws PosicionInvalidaException{
		Posicion posicion1 = new Posicion(2,3);
		Posicion posicion2 = new Posicion(2,3);
		Assert.assertEquals(posicion1, posicion2);
	}
	
	@Test
	public void testComparoDosPosicionesDistintasLaComparacionDevuelveFalse() throws PosicionInvalidaException{
		Posicion posicion1 = new Posicion(2,3);
		Posicion posicion2 = new Posicion(4,5);
		Assert.assertNotEquals(posicion1, posicion2);
	}
	
	
	
}
