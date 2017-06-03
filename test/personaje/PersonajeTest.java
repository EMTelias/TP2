package personaje;

import org.junit.Test;
import org.junit.Assert;

import tablero.Casillero;
import tablero.CasilleroOcupadoException;
import tablero.DimensionDeTableroInvalidoException;
import tablero.Posicion;
import tablero.PosicionInvalidaException;
import tablero.Tablero;

public class PersonajeTest {

	@Test
	public void testColocoUnGokuYMuevoEnModoNormal() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, PosicionInvalidaException, NoPuedeMoverAEsaDistanciaException{		
		Tablero tablero = new Tablero(10,10);
		Personaje goku = new Goku();
		Posicion posicionInicial= new Posicion(2,3);
		Posicion posicionFinal= new Posicion(3,3);
		Casillero casillero = tablero.getCasillero(posicionFinal);
		
		
		Assert.assertTrue( tablero.estaVacioEn(posicionInicial));
		Assert.assertTrue( tablero.estaVacioEn(posicionFinal));
		
		tablero.colocar(goku,posicionInicial);
		Assert.assertFalse( tablero.estaVacioEn(posicionInicial));
		Assert.assertTrue( tablero.estaVacioEn(posicionFinal));
				
		goku.moverA(casillero);
		Assert.assertTrue( tablero.estaVacioEn(posicionInicial));
		Assert.assertFalse( tablero.estaVacioEn(posicionFinal));
	}
	
	@Test(expected = NoPuedeMoverAEsaDistanciaException.class)
	public void testMuevoGokuEnModoNormalExcediendoVelocidadDevuelveNoPuedeMoverAEsaDistanciaException() throws DimensionDeTableroInvalidoException, PosicionInvalidaException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException{
		Tablero tablero = new Tablero(10,10);
		Personaje goku = new Goku();
		Posicion posicionInicial= new Posicion(2,3);
		Posicion posicionFinal= new Posicion(5,3);
		Casillero casillero = tablero.getCasillero(posicionFinal);
		
		tablero.colocar(goku,posicionInicial);
		goku.moverA(casillero);
	}
	
	//public void testSiNoPuedeMoverQuedaEnElCasilleroEnElQueEstaba(){}
	
}
