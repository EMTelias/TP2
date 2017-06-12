package tablero;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;

public class CaminoTest {

	@Test
	public void testCreoCaminoCon2CasillerosDistanciaDeCaminoDevuelve2() throws CaminoInvalidoException{
		List<Casillero> casilleros = new ArrayList<Casillero>();
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(2,2));
		
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		
		Camino camino = new Camino(casilleros);
		
		Assert.assertEquals(camino.distancia(),2);	
	}
	
	@Test (expected = CaminoInvalidoException.class)
	public void testCreoCaminoCon0CasillerosDevuelveCaminoInvalidoException() throws CaminoInvalidoException{
		List<Casillero> casilleros = new ArrayList<Casillero>();
		new Camino(casilleros);
	}
	
}
