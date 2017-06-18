package tablero;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;

public class CaminoTest {

	@Test (expected = CaminoInvalidoException.class)
	public void testCreoCaminoCon0CasillerosDevuelveCaminoInvalidoException() throws CaminoInvalidoException{
		List<Casillero> casilleros = new ArrayList<Casillero>();
		new Camino(casilleros);
	}
	
}
