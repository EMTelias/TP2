package tablero;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CaminoTest {

	@Test
	public void testCreoCaminoCon2CasillerosDistanciaDeCaminoDevuelve1(){
		List<Casillero> casilleros = new ArrayList<Casillero>();
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(2,2));
		
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		
		Camino camino = new Camino(casilleros);
		
		Assert.assertEquals(camino.distancia(),1);	
	}
	
	//El camino al crearse tiene que tener al menos 2 casilleros sino excepcion (hacer test y codearlo (recordatorio para mi: Alexis))
	
	
}
