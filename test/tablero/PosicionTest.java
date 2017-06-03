package tablero;

import org.junit.Assert;
import org.junit.Test;

import tablero.Posicion;

public class PosicionTest {


	public void testCreoPosicionDevuelveValoresXeYCorrectos(){
		Posicion posicion = new Posicion(2,4);
		Assert.assertEquals(posicion.getPosicionX(), 2);
		Assert.assertEquals(posicion.getPosicionY(), 4);
	}
	
	@Test
	public void testComparoDosPosicionesIgualesLaComparacionDevuelveTrue(){
		Posicion posicion1 = new Posicion(2,3);
		Posicion posicion2 = new Posicion(2,3);
		Assert.assertEquals(posicion1, posicion2);
	}
	
	@Test
	public void testComparoDosPosicionesDistintasLaComparacionDevuelveFalse(){
		Posicion posicion1 = new Posicion(2,3);
		Posicion posicion2 = new Posicion(4,5);
		Assert.assertNotEquals(posicion1, posicion2);
	}
	
	@Test
	public void testVerificaDistancias(){
		Posicion posicion11 = new Posicion(1,1);
		Posicion posicion22 = new Posicion(2,2);
		Posicion posicion33 = new Posicion(3,3);
		Posicion posicion17 = new Posicion(1,7);
		
		Assert.assertTrue( posicion11.distanciaHasta(posicion22) == 1);
		Assert.assertTrue( posicion11.distanciaHasta(posicion33) == 2);	
		Assert.assertTrue( posicion11.distanciaHasta(posicion17) == 6);	
		
	}
	
}
