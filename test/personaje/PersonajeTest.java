package personaje;

import estado.goku.KaioKenGoku;
import estado.goku.SuperSayajinGoku;
import excepciones.direccion.NoHayDireccionPosibleException;
import excepciones.estado.EstadoNoTieneProximoException;
import excepciones.personaje.NoPuedeCambiarDeEstadoKiInsuficienteException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import org.junit.Assert;
import org.junit.Test;
import tablero.Casillero;
import tablero.Posicion;
import tablero.Tablero;

public class PersonajeTest {

	
	// --------- Tests de movimiento ----------------
	@Test
	public void testColocoUnGokuYMuevoEnModoNormal() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoHayDireccionPosibleException{
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
	public void testMuevoGokuEnModoNormalExcediendoVelocidadDevuelveNoPuedeMoverAEsaDistanciaException() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoHayDireccionPosibleException{
		Tablero tablero = new Tablero(10,10);
		Personaje goku = new Goku();
		Posicion posicionInicial= new Posicion(2,3);
		Posicion posicionFinal= new Posicion(5,3);
		Casillero casillero = tablero.getCasillero(posicionFinal);
		
		tablero.colocar(goku,posicionInicial);
		goku.moverA(casillero);
	}
	
	@Test(expected = NoPuedeMoverCaminoObstruidoException.class)
	public void testQuieroMoverAGokuConOtroPersonajeEnElMedioDevuelveNoPuedeMoverCaminoObstruidoException() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoHayDireccionPosibleException{
		Tablero tablero = new Tablero(10,10);
		Personaje goku = new Goku();
		Personaje gohan = new Gohan();
		Posicion posicionInicialGoku = new Posicion(2,3);
		Posicion posicionInicialGohan = new Posicion(3,3);
		Posicion posicionFinal= new Posicion(4,3);
		
		Casillero casillero = tablero.getCasillero(posicionFinal);
		
		tablero.colocar(goku,posicionInicialGoku);
		tablero.colocar(gohan, posicionInicialGohan);
		
		goku.moverA(casillero);
	}

	@Test 
	public void testGokuEnModoNormalPuedeMover2PosicionesYEnPrimeraTransformacionPuedeMover3Posiciones() throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoHayDireccionPosibleException, CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException{
		Tablero tablero = new Tablero(10,10);
		Personaje goku = new Goku();
		Posicion posicion11= new Posicion(1,1);
		Posicion posicion31= new Posicion(3,1);
		Posicion posicion41= new Posicion(4,1);
		Posicion posicion61= new Posicion(6,1);
		Posicion posicion71= new Posicion(7,1);
		
		tablero.colocar(goku,posicion11);
		
		Casillero casillero41 = tablero.getCasillero(posicion41);
		try{	
			goku.moverA(casillero41);
			Assert.assertTrue(false);
		}catch(NoPuedeMoverAEsaDistanciaException e){
			Assert.assertTrue(true);
		}
		
		Casillero casillero31 = tablero.getCasillero(posicion31);
		goku.moverA(casillero31);
		Assert.assertFalse(tablero.estaVacioEn(posicion31));
		Assert.assertTrue(tablero.estaVacioEn(posicion11));
		
		goku.aumentarKi(20);
		goku.transformar();
		
		Casillero casillero71 = tablero.getCasillero(posicion71);
		try{	
			goku.moverA(casillero71);
			Assert.assertTrue(false);
		}catch(NoPuedeMoverAEsaDistanciaException e){
			Assert.assertTrue(true);
		}
		
		Casillero casillero61 = tablero.getCasillero(posicion61);
		goku.moverA(casillero61);
		Assert.assertFalse(tablero.estaVacioEn(posicion61));
		Assert.assertTrue(tablero.estaVacioEn(posicion31));
		
		
		
	}
	
	
	// --------------- Test de transformacion --------------
	@Test(expected = NoPuedeCambiarDeEstadoKiInsuficienteException.class)
	public void testTransformoAGokuCon0KiDevuelveNopuedeCambiarDeEstadoKiInsuficienteException() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.transformar();
	}

	@Test(expected = EstadoNoTieneProximoException.class)
	public void testTransformoAGokuSuperSayajinDevuelveEstadoNoTieneProximoException() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.estado = new SuperSayajinGoku();
		goku.transformar();
	}

	@Test public void testTransformoAGokuNormalYRevisoQueTengaEstadoKaioKen() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.aumentarKi(20);
		goku.transformar();
		Assert.assertTrue(goku.estado.getClass() == KaioKenGoku.class);
	}

	@Test public void testTransformoAGokuCon80DeKiYSuKiTieneQueBajarA60() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.aumentarKi(80);
		goku.transformar();
		Assert.assertTrue(goku.ki == 60);
	}

	@Test public void testTransformoAGokuNormal2VecesYVerificoQueSuKiPaseDe100A30() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.aumentarKi(100);
		goku.transformar();
		goku.transformar();
		Assert.assertTrue(goku.ki == 30);
	}

	@Test public void testTransformoAGokuNormal2VecesYVerificoQueTengaEstadoSuperSayajin() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException {
		Personaje goku = new Goku();
		goku.aumentarKi(100);
		goku.transformar();
		goku.transformar();
		Assert.assertTrue(goku.estado.getClass() == SuperSayajinGoku.class);
	}

}
