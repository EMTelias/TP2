package posicion;

import org.junit.Test;

public class PosicionTest {

	@Test(expected=PosicionInvalidaException.class)
	public void testCreoPosicionConValorNegativoEnXDevuelvePosicionInvalidaException() throws PosicionInvalidaException{
		new Posicion(-2,3);
	}
	
	@Test(expected=PosicionInvalidaException.class)
	public void testCreoPosicionConValorNegativoEnYDevuelvePosicionInvalidaException() throws PosicionInvalidaException{
		new Posicion(3,-2);
	}
	
}
