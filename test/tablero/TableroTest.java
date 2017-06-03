package tablero;

import org.junit.Test;

public class TableroTest {

	@Test(expected = DimensionDeTableroInvalidoException.class)
	public void testAlCrearTableroConValorNegativoDevuelveDimensionDeTableroInvalidoException() throws DimensionDeTableroInvalidoException{
		new Tablero(-2,3);
	}
	
}
