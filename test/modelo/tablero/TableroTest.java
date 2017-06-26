package modelo.tablero;

import modelo.excepciones.tablero.DimensionDeTableroInvalidoException;
import org.junit.Test;
import org.junit.Assert;

public class TableroTest {

	@Test(expected = DimensionDeTableroInvalidoException.class)
	public void testAlCrearTableroConValorNegativoDevuelveDimensionDeTableroInvalidoException() {
		new Tablero(-2,3);
	}

	@Test
	public void testAlCrearConsultoSiUnLugarEstaVacioDevuelveTrue() {
		Tablero tablero = new Tablero(10,10);
		Posicion posicion = new Posicion(3,4);
		Assert.assertTrue(tablero.estaVacioEn(posicion));	
	}
	
	
	
}
