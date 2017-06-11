package personaje;

import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.transformacion.KiInsuficienteException;
import excepciones.transformacion.NoPuedeTransformarException;
import transformacion.cell.PerfectoCell;
import transformacion.cell.SemiPerfectoCell;
import transformacion.freezer.SegundaFormaFreezer;
import transformacion.goku.SuperSayajinGoku;
import transformacion.gohan.SuperSayajin2Gohan;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import excepciones.tablero.NoHayQuienRecorraException;

import java.util.ArrayList;

import excepciones.transformacion.NoHayProximaTransformacionException;
import org.junit.Assert;
import org.junit.Test;

import tablero.Camino;
import tablero.Casillero;
import tablero.Posicion;

public class PersonajeTest {

	
	// --------- Tests de movimiento ----------------
	@Test
	public void testGokuEnModoNormalSeMueve2CasillerosEnUnCaminoDespejadoNoDevuelveExcepcion() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException, NoHayQuienRecorraException{
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(1,2));
		Casillero casillero3 = new Casillero(new Posicion(1,3));
		
		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		casilleros.add(casillero3);
		
		Personaje goku = new Goku(casillero1);
		Camino camino = new Camino(casilleros);
		
		goku.mover(camino);
		Assert.assertFalse(casillero3.estaVacio());
	}
	
	@Test(expected = NoPuedeMoverAEsaDistanciaException.class)
	public void testGokuEnModoNormalSeMueve3CasillerosExcediendoVelocidadDevuelveNoPuedeMoverAEsaDistanciaException() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException, NoHayQuienRecorraException{
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(1,2));
		Casillero casillero3 = new Casillero(new Posicion(1,3));
		Casillero casillero4 = new Casillero(new Posicion(1,4));
		
		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		casilleros.add(casillero3);
		casilleros.add(casillero4);
		
		Personaje goku = new Goku(casillero1);
		Camino camino = new Camino(casilleros);
		
		goku.mover(camino);
	}
	
	@Test
	public void testGokuEnModoKaioKenSeMueve3CasillerosEnUnCaminoDespejadoNoDevuelveExcepcion() throws CasilleroOcupadoException, CaminoInvalidoException, NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException, NoHayQuienRecorraException{
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(1,2));
		Casillero casillero3 = new Casillero(new Posicion(1,3));
		Casillero casillero4 = new Casillero(new Posicion(1,4));
		
		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		casilleros.add(casillero3);
		casilleros.add(casillero4);
		
		Personaje goku = new Goku(casillero1);
		Camino camino = new Camino(casilleros);
		
		goku.aumentarKi(20);
		goku.transformar();
		
		goku.mover(camino);
		Assert.assertFalse(casillero4.estaVacio());
	}
	
	@Test(expected = NoPuedeMoverAEsaDistanciaException.class)
	public void testGokuEnModoKaioKenSeMueve4CasillerosExcediendoVelocidadDevuelveNoPuedeMoverAEsaDistanciaException() throws CasilleroOcupadoException, CaminoInvalidoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException, NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException, NoHayQuienRecorraException{
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(1,2));
		Casillero casillero3 = new Casillero(new Posicion(1,3));
		Casillero casillero4 = new Casillero(new Posicion(1,4));
		Casillero casillero5 = new Casillero(new Posicion(1,5));
		
		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		casilleros.add(casillero3);
		casilleros.add(casillero4);
		casilleros.add(casillero5);
		
		Personaje goku = new Goku(casillero1);
		Camino camino = new Camino(casilleros);
		
		goku.aumentarKi(20);
		goku.transformar();
		
		goku.mover(camino);
		Assert.assertFalse(casillero4.estaVacio());
	}
	
	@Test(expected = NoPuedeMoverCaminoObstruidoException.class)
	public void testGokuEnModoNormalQuiereMoverPeroEstaGohanEnMedioDelCaminoDevuelveNoPuedeMoverCaminoObstruidoException() throws CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException, NoHayQuienRecorraException{
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(1,2));
		Casillero casillero3 = new Casillero(new Posicion(1,3));
		
		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		casilleros.add(casillero3);
		
		Personaje goku = new Goku(casillero1);
		Personaje gohan = new Gohan(casillero2);
		Camino camino = new Camino(casilleros);
		
		goku.mover(camino);
	}
	
	@Test(expected = NoPuedeMoverCaminoObstruidoException.class)
	public void testGokuEnModoNormalEnElCasilleroDestinoEstaGohanDevuelveNoPuedeMoverCaminoObstruidoException() throws CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException, NoHayQuienRecorraException{
		Casillero casillero1 = new Casillero(new Posicion(1,1));
		Casillero casillero2 = new Casillero(new Posicion(1,2));
		Casillero casillero3 = new Casillero(new Posicion(1,3));
		
		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		casilleros.add(casillero3);
		
		Personaje goku = new Goku(casillero1);
		Personaje gohan = new Gohan(casillero3);
		Camino camino = new Camino(casilleros);
		
		goku.mover(camino);
	}



	//Tests de transformacion por ki de los personajes

	@Test
	public void testCreoUnGokuNormalYLoTransformoEnKaioKenGokuEntoncesSuKiDisminuye20() throws CasilleroOcupadoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		goku.aumentarKi(20);

		goku.transformar();
		Assert.assertTrue(goku.getKi() == 0);
	}

	@Test
	public void testCreoUnGokuNormalYLoTransformoEnKaioKenGokuEntoncesSuTransformacionEsKaioKenGoku() throws CasilleroOcupadoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Goku goku = new Goku(new Casillero(new Posicion(1, 1)));
		goku.aumentarKi(20);

		goku.transformar();
		Assert.assertTrue(goku.transformacion.getClass() == transformacion.goku.KaioKenGoku.class);
	}

	@Test(expected = KiInsuficienteException.class)
	public void testCreoUnGokuNormalYLoTransformoEnKaioKenGokuSinKiSuficienteEntoncesLanzaNoPuedeTransformarKiInsuficiente() throws NoHayProximaTransformacionException, CasilleroOcupadoException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		goku.transformar();
	}

	@Test(expected = KiInsuficienteException.class)
	public void testCreoUnGohanNormalYLoTransformoEnSuperSayajinGohanSinKiSuficienteEntoncesLanzaNoPuedeTransformarKiInsuficiente() throws NoHayProximaTransformacionException, CasilleroOcupadoException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje gohan = new Gohan(new Casillero(new Posicion(1,1)));
		gohan.transformar();
	}


	@Test(expected = NoHayProximaTransformacionException.class)
	public void testCreoUnGokuSuperSayajinYLoTransformoSinTenerProximaTransformacionEntoncesLanzaNoHayProximaTransformacion() throws CasilleroOcupadoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		goku.transformacion = new SuperSayajinGoku();
		goku.transformar();
	}


	@Test(expected = NoHayProximaTransformacionException.class)
	public void testCreoUnGohanSuperSayajin2YLoTransformoSinTenerProximaTransformacionEntoncesLanzaNoHayProximaTransformacion() throws CasilleroOcupadoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje gohan = new Gohan(new Casillero(new Posicion(1,1)));
		gohan.transformacion = new SuperSayajin2Gohan();
		gohan.transformar();
	}

	@Test
	public void testTransformoAGokuCon80DeKiYSuKiTieneQueBajarA60() throws NoHayProximaTransformacionException, CasilleroOcupadoException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		goku.aumentarKi(80);
		goku.transformar();
		Assert.assertTrue(goku.getKi() == 60);
	}


	@Test
	public void testTransformoAGokuNormal2VecesYVerificoQueSuKiPaseDe100A30() throws CasilleroOcupadoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		goku.aumentarKi(100);
		goku.transformar();
		goku.transformar();
		Assert.assertTrue(goku.getKi() == 30);
	}

	@Test
	public void testTransformoAGokuNormal2VecesYVerificoQueSuTransformacionSeaSuperSayajinGoku() throws CasilleroOcupadoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		goku.aumentarKi(100);
		goku.transformar();
		goku.transformar();
		Assert.assertTrue(goku.transformacion.getClass() == SuperSayajinGoku.class);
	}

	// Tests de ataques basicos entre personajes
	@Test
	public void testGokuNormalAtacaConElAtaqueBasicoAFreezerNormalYLeDejaLaVidaEn380() throws NoPuedeAtacarAEsaDistanciaException, CasilleroOcupadoException {
		Personaje goku = new Goku(new Casillero(new Posicion(2,2)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(2,3)));

		goku.atacarA(freezer);

		Assert.assertTrue(freezer.vida == 380);
	}


	@Test
	public void testGokuNormalAtacaConElAtaqueBasicoAFreezerSegundaFormaYLeDejaLaVidaEn380() throws NoPuedeAtacarAEsaDistanciaException, CasilleroOcupadoException {
		Personaje goku = new Goku(new Casillero(new Posicion(2,2)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(2,3)));
		freezer.transformacion = new SegundaFormaFreezer();

		goku.atacarA(freezer);

		Assert.assertTrue(freezer.vida == 384);
	}

	@Test (expected = NoPuedeAtacarAEsaDistanciaException.class)
	public void testGokuNormalAtacaAFreezerNormalEstandoADistancia3LanzaNoPuedeAtacarAEsaDistanciaException() throws NoPuedeAtacarAEsaDistanciaException, CasilleroOcupadoException {
		Personaje goku = new Goku(new Casillero(new Posicion(2,2)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(2,5)));
		goku.atacarA(freezer);

	}

	@Test
	public void testColocoAGohanSS2En33YFreezerNormalEn66YHaceAtaqueBasicoDejandoloEn300() throws NoPuedeAtacarAEsaDistanciaException, CasilleroOcupadoException {
		// Poder de pelea de Gohan SS2: 100. Poder de pelea de Freezer normal: 20.
		Personaje gohan = new Gohan(new Casillero(new Posicion(3,3)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(6,6)));
		gohan.transformacion = new SuperSayajin2Gohan();

		gohan.atacarA(freezer);
		Assert.assertTrue(freezer.vida == 300);
	}

	// Tests de transformaciones especiales

	// Cell

	@Test(expected = NoPuedeTransformarException.class)
	public void testCellIntentaTransformarseSinAbsorber() throws CasilleroOcupadoException, KiInsuficienteException, NoHayProximaTransformacionException, NoPuedeTransformarException {
		// Poder de pelea de Cell Normal = 20. Poder de pelea Piccolo = 20. Danio 20. Vida Cell aumenta 20.
		Personaje cell = new Cell(new Casillero(new Posicion(1,1)));

		cell.transformar();

	}

	@Test
	public void testCellIntentaTransformarseEnSemiPerfectoAbsorbiendoPreviamente() throws CasilleroOcupadoException, KiInsuficienteException, NoHayProximaTransformacionException, NoPuedeTransformarException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
		// Poder de pelea de Cell Normal = 20. Poder de pelea Piccolo = 20. Danio 20. Vida Cell aumenta 20.
		Personaje cell = new Cell(new Casillero(new Posicion(1,1)));
		Personaje goku = new Goku(new Casillero(new Posicion(3,1)));

		for(int i= 1; i <=4; i++) {
			cell.aumentarKi(5);
			cell.ataqueEspecialA(goku);
		}

		cell.transformar();
		Assert.assertEquals(SemiPerfectoCell.class, cell.transformacion.getClass());
	}

	@Test
	public void testCellIntentaTransformarseEnPerfectoAbsorbiendoPreviamente() throws CasilleroOcupadoException, KiInsuficienteException, NoHayProximaTransformacionException, NoPuedeTransformarException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
		// Poder de pelea de Cell Normal = 20. Poder de pelea Piccolo = 20. Danio 20. Vida Cell aumenta 20.
		Personaje cell = new Cell(new Casillero(new Posicion(1,1)));
		Personaje goku = new Goku(new Casillero(new Posicion(3,1)));

		for(int i= 1; i <=8; i++) {
			cell.aumentarKi(5);
			cell.ataqueEspecialA(goku);
		}

		cell.transformar();
		cell.transformar();
		Assert.assertEquals(PerfectoCell.class, cell.transformacion.getClass());
	}



	// Tests ataques especiales

	@Test(expected = NoPuedeAtacarMismoEquipoException.class)
	public void testGokuNormalHaceAtaqueEspecialASuEquipoDebeLanzarNoPuedeAtacarMismoEquipoException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(2,2)));

		goku.ataqueEspecialA(gohan);
	}

	@Test(expected = NoPuedeAtacarAEsaDistanciaException.class)
	public void testGokuNormalHaceAtaqueEspecialAFreezerADistancia3DebeLanzarNoPuedeAtacarAEsaDistanciaException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(4,1)));
		goku.aumentarKi(20);
		goku.ataqueEspecialA(freezer);
	}

	@Test
	public void testGokuNormalHaceAtaqueEspecialAFreezerNormalLeDebeSacar30DeVidaDejandoloEn370() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		//Poder ataque goku= 20 .. Poder ataque freezer 20. Ataque especial hace 50% mas danio que 20 --> 30
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(3,1)));
		goku.aumentarKi(20);
		goku.ataqueEspecialA(freezer);

		Assert.assertTrue(freezer.vida == 370);
	}

	@Test
	public void testGokuNormalHaceAtaqueEspecialAMajinBooNormalLeDebeSacar24DeVidaDejandoloEn276() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		//Poder ataque goku= 20 .. Poder ataque majin boo 30.(-20% danio) Ataque especial hace 50% mas danio que 20*0.8=16 --> 24
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		Personaje majinBoo = new MajinBoo(new Casillero(new Posicion(3,1)));
		goku.aumentarKi(20);
		goku.ataqueEspecialA(majinBoo);

		Assert.assertTrue(majinBoo.vida == 276);
	}

	@Test
	public void testFreezerSegundaFormaHaceAtaqueEspecialAPiccoloNormalLeDebeSacar24DeVidaDejandoloEn276() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException, NoHayProximaTransformacionException, NoPuedeTransformarException {
		//Poder ataque freezer 2daforma= 40 .. Poder ataque piccolo 20. Ataque especial hace 50% mas danio que 40 --> 60
		Personaje freezer = new Freezer(new Casillero(new Posicion(1,1)));
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(3,1)));
		freezer.aumentarKi(40);
		freezer.transformar();
		freezer.ataqueEspecialA(piccolo);

		Assert.assertTrue(piccolo.vida == 440);
	}

	@Test
	public void testCellRealizaAtaqueEspecialAbsorberYSuVidaSeIncrementaLaMismaCantidadDeVidaQuePerdioElAtacado() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		// Poder de pelea de Cell Normal = 20. Poder de pelea Piccolo = 20. Danio 20. Vida Cell aumenta 20.
		Personaje cell = new Cell(new Casillero(new Posicion(1,1)));
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(2,2)));
		cell.aumentarKi(5);
		int vidaPreviaCell = cell.getVida();
		cell.ataqueEspecialA(piccolo);

		Assert.assertEquals(20, cell.getVida() - vidaPreviaCell);
	}

	@Test(expected = NoPuedeAtacarMismoEquipoException.class)
	public void testFreezerNormalHaceAtaqueEspecialASuEquipoDebeLanzarNoPuedeAtacarMismoEquipoException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje freezer = new Freezer(new Casillero(new Posicion(1,1)));
		Personaje cell = new Cell(new Casillero(new Posicion(2,2)));

		freezer.ataqueEspecialA(cell);
	}

}
