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
	public void testGokuSeMueveEnUnCaminoDespejadoNoDevuelveExcepcion() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException{
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
	
	@Test(expected = NoPuedeMoverCaminoObstruidoException.class)
	public void testGokuQuiereMoverPeroEstaGohanEnMedioDelCaminoDevuelveNoPuedeMoverCaminoObstruidoException() throws CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException{
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
	
	@Test(expected = NoPuedeMoverAEsaDistanciaException.class)
	public void testGokuQuiereMoverEnModoNormalExcediendoVelocidadDevuelveNoPuedeMoverAEsaDistanciaException() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException{
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


/*

	@Test
	public void testGokuEnModoNormalPuedeMover2PosicionesYEnPrimeraTransformacionPuedeMover3Posiciones() throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException{
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


	@Test public void testCreoAGokuYTiene500PuntosDeVida(){
		Personaje goku = new Goku();
		Assert.assertTrue(goku.vida == 500);
	}



	@Test public void testGohanNormalAtacaAGokuDaniando20PorcientoMenosDejandoloEn488() throws NoPuedeAtacarAEsaDistanciaException, CasilleroOcupadoException, DimensionDeTableroInvalidoException {
		//Poder de ataque de gohan: 15 / Poder de ataque de goku: 20 => gohan daña 20% menos(12dmg)
		Posicion posicion1 = new Posicion(2,3);
		Posicion posicion2 = new Posicion(2,4);
		Personaje goku = new Goku();
		Personaje gohan = new Gohan();

		Tablero tablero = new Tablero(10,10);
		tablero.colocar(goku,posicion1);
		tablero.colocar(gohan,posicion2);

		gohan.ataqueBasicoA(goku);
		Assert.assertTrue(goku.vida == 488);

	}

	@Test public void testGokuNormalAtacaAGohanNormalDejandoloEn280() throws NoPuedeAtacarAEsaDistanciaException, DimensionDeTableroInvalidoException, CasilleroOcupadoException {
		Posicion posicion1 = new Posicion(2,3);
		Posicion posicion2 = new Posicion(2,4);
		Personaje goku = new Goku();
		Personaje gohan = new Gohan();

		Tablero tablero = new Tablero(10,10);
		tablero.colocar(goku,posicion1);
		tablero.colocar(gohan,posicion2);

		goku.ataqueBasicoA(gohan);
		Assert.assertTrue(gohan.vida == 280);
	}

	@Test
	public void testCreoUnTablero20x20ColocoAGokuNormalEn44YFreezerNormalEn66YHaceAtaqueBasicoDejandoloEn380() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeAtacarAEsaDistanciaException {
		Tablero tablero = new Tablero(20,20);
		Personaje goku = new Goku();
		Personaje freezer = new Freezer();
		Posicion posicionGoku = new Posicion(4,4);
		Posicion posicionFreezer = new Posicion(6,6);
		tablero.colocar(goku, posicionGoku);
		tablero.colocar(freezer, posicionFreezer);

		goku.ataqueBasicoA(freezer);
		Assert.assertTrue(freezer.vida == 380);
	}

	@Test(expected = NoPuedeAtacarAEsaDistanciaException.class)
	public void testCreoUnTablero20x20ColocoAGohanSS2En33YFreezerNormalEn1010YHaceAtaqueBasicoLanzandoExcepcionNoPuedeAtacar() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeAtacarAEsaDistanciaException {
		// Poder de pelea de Gohan SS2: 100. Poder de pelea de Freezer normal: 50.
		Tablero tablero = new Tablero(20,20);
		Personaje gohan = new Gohan();
		Personaje freezer = new Freezer();
		gohan.estado = new SuperSayajin2Gohan();
		Posicion posicionGohan = new Posicion(3,3);
		Posicion posicionFreezer = new Posicion(10,10);
		tablero.colocar(gohan, posicionGohan);
		tablero.colocar(freezer, posicionFreezer);

		gohan.ataqueBasicoA(freezer);
		Assert.assertTrue(freezer.vida == 400);
	}

*/
}
