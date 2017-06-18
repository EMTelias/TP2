package modelo.tablero;

import org.junit.Assert;
import org.junit.Test;

public class PosicionTest {

	@Test
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
	public void testVerificaDistanciasAlineadas(){
		Posicion posicion11 = new Posicion(1,1);
		Posicion posicion31 = new Posicion(3,1);
		Posicion posicion14 = new Posicion(1,4);
		Posicion posicion66 = new Posicion(6,6);
		
		Assert.assertTrue( posicion11.distanciaHasta(posicion31) == 2);
		Assert.assertTrue( posicion11.distanciaHasta(posicion14) == 3);	
		Assert.assertTrue( posicion11.distanciaHasta(posicion66) == 5);	
	}
	
	@Test
	public  void testVerificaDistanciaNoAlineada(){
		Posicion posicion11 = new Posicion(1,1);
		Posicion posicion32 = new Posicion(3,2);
		Posicion posicion24 = new Posicion(2,4);
		
		Assert.assertTrue( posicion11.distanciaHasta(posicion32) == 2);
		Assert.assertTrue( posicion11.distanciaHasta(posicion24) == 3);
	}
}
