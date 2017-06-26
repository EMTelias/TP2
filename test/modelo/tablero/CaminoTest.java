package modelo.tablero;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import modelo.excepciones.tablero.CaminoInvalidoException;

public class CaminoTest {

	@Test (expected = CaminoInvalidoException.class)
	public void testCreoCaminoCon0CasillerosDevuelveCaminoInvalidoException() {
		List<Casillero> casilleros = new ArrayList<Casillero>();
		new Camino(casilleros);
	}

	@Test
	public void testCreoCaminoCon3CasillerosYElPrimerCasilleroEsElPrimeroQueAgregue() {
		List<Casillero> casilleros = new ArrayList<Casillero>();
		Casillero primerCasillero = new Casillero(new Posicion(0,0));
		casilleros.add(primerCasillero);
		casilleros.add(new Casillero(new Posicion(1,1)));
		casilleros.add(new Casillero(new Posicion(2,2)));
		
		Camino camino = new Camino(casilleros);
		Assert.assertEquals(camino.getPrimerCasillero(),primerCasillero);

	}
	
}
