package personaje;

import equipo.Equipo;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import excepciones.transformacion.KiInsuficienteException;
import excepciones.transformacion.NoHayProximaTransformacionException;
import excepciones.transformacion.NoPuedeTransformarException;
import org.junit.Assert;
import org.junit.Test;
import tablero.Camino;
import tablero.Casillero;
import tablero.Posicion;
import transformacion.cell.PerfectoCell;
import transformacion.cell.SemiPerfectoCell;
import transformacion.freezer.SegundaFormaFreezer;
import transformacion.gohan.SuperSayajin2Gohan;
import transformacion.goku.SuperSayajinGoku;
import transformacion.piccolo.ProtectorPiccolo;

import java.util.ArrayList;

public class PersonajeTest {


	// --------- Tests de movimiento ----------------
	@Test
	public void testGokuEnModoNormalSeMueve2CasillerosEnUnCaminoDespejadoNoDevuelveExcepcion() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException {
		Casillero casillero1 = new Casillero(new Posicion(1, 1));
		Casillero casillero2 = new Casillero(new Posicion(1, 2));
		Casillero casillero3 = new Casillero(new Posicion(1, 3));

		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero2);
		casilleros.add(casillero3);

		Personaje goku = new Goku(casillero1);
		Camino camino = new Camino(casilleros);

		goku.mover(camino);
		Assert.assertTrue(casillero1.estaVacio());
		Assert.assertFalse(casillero3.estaVacio());
	}

	@Test(expected = NoPuedeMoverAEsaDistanciaException.class)
	public void testGokuEnModoNormalSeMueve3CasillerosExcediendoVelocidadDevuelveNoPuedeMoverAEsaDistanciaException() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException {
		Casillero casillero1 = new Casillero(new Posicion(1, 1));
		Casillero casillero2 = new Casillero(new Posicion(1, 2));
		Casillero casillero3 = new Casillero(new Posicion(1, 3));
		Casillero casillero4 = new Casillero(new Posicion(1, 4));

		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero2);
		casilleros.add(casillero3);
		casilleros.add(casillero4);

		Personaje goku = new Goku(casillero1);
		Camino camino = new Camino(casilleros);

		goku.mover(camino);
	}

	@Test
	public void testGokuEnModoKaioKenSeMueve3CasillerosEnUnCaminoDespejadoNoDevuelveExcepcion() throws CasilleroOcupadoException, CaminoInvalidoException, NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Casillero casillero1 = new Casillero(new Posicion(1, 1));
		Casillero casillero2 = new Casillero(new Posicion(1, 2));
		Casillero casillero3 = new Casillero(new Posicion(1, 3));
		Casillero casillero4 = new Casillero(new Posicion(1, 4));

		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
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
	public void testGokuEnModoKaioKenSeMueve4CasillerosExcediendoVelocidadDevuelveNoPuedeMoverAEsaDistanciaException() throws CasilleroOcupadoException, CaminoInvalidoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException, NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException {
		Casillero casillero1 = new Casillero(new Posicion(1, 1));
		Casillero casillero2 = new Casillero(new Posicion(1, 2));
		Casillero casillero3 = new Casillero(new Posicion(1, 3));
		Casillero casillero4 = new Casillero(new Posicion(1, 4));
		Casillero casillero5 = new Casillero(new Posicion(1, 5));

		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
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
	public void testGokuEnModoNormalQuiereMoverPeroEstaGohanEnMedioDelCaminoDevuelveNoPuedeMoverCaminoObstruidoException() throws CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException {
		Casillero casillero1 = new Casillero(new Posicion(1, 1));
		Casillero casillero2 = new Casillero(new Posicion(1, 2));
		Casillero casillero3 = new Casillero(new Posicion(1, 3));

		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero2);
		casilleros.add(casillero3);

		Personaje goku = new Goku(casillero1);
		Personaje gohan = new Gohan(casillero2);
		Camino camino = new Camino(casilleros);

		goku.mover(camino);
	}

	@Test(expected = NoPuedeMoverCaminoObstruidoException.class)
	public void testGokuEnModoNormalEnElCasilleroDestinoEstaGohanDevuelveNoPuedeMoverCaminoObstruidoException() throws CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException {
		Casillero casillero1 = new Casillero(new Posicion(1, 1));
		Casillero casillero2 = new Casillero(new Posicion(1, 2));
		Casillero casillero3 = new Casillero(new Posicion(1, 3));

		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
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
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		goku.transformar();
	}

	@Test(expected = KiInsuficienteException.class)
	public void testCreoUnGohanNormalYLoTransformoEnSuperSayajinGohanSinKiSuficienteEntoncesLanzaNoPuedeTransformarKiInsuficiente() throws NoHayProximaTransformacionException, CasilleroOcupadoException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje gohan = new Gohan(new Casillero(new Posicion(1, 1)));
		gohan.transformar();
	}


	@Test(expected = NoHayProximaTransformacionException.class)
	public void testCreoUnGokuSuperSayajinYLoTransformoSinTenerProximaTransformacionEntoncesLanzaNoHayProximaTransformacion() throws CasilleroOcupadoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		goku.transformacion = new SuperSayajinGoku();
		goku.transformar();
	}


	@Test(expected = NoHayProximaTransformacionException.class)
	public void testCreoUnGohanSuperSayajin2YLoTransformoSinTenerProximaTransformacionEntoncesLanzaNoHayProximaTransformacion() throws CasilleroOcupadoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje gohan = new Gohan(new Casillero(new Posicion(1, 1)));
		gohan.transformacion = new SuperSayajin2Gohan();
		gohan.transformar();
	}

	@Test
	public void testTransformoAGokuCon80DeKiYSuKiTieneQueBajarA60() throws NoHayProximaTransformacionException, CasilleroOcupadoException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		goku.aumentarKi(80);
		goku.transformar();
		Assert.assertTrue(goku.getKi() == 60);
	}


	@Test
	public void testTransformoAGokuNormal2VecesYVerificoQueSuKiPaseDe100A30() throws CasilleroOcupadoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		goku.aumentarKi(100);
		goku.transformar();
		goku.transformar();
		Assert.assertTrue(goku.getKi() == 30);
	}

	@Test
	public void testTransformoAGokuNormal2VecesYVerificoQueSuTransformacionSeaSuperSayajinGoku() throws CasilleroOcupadoException, NoHayProximaTransformacionException, KiInsuficienteException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		goku.aumentarKi(100);
		goku.transformar();
		goku.transformar();
		Assert.assertTrue(goku.transformacion.getClass() == SuperSayajinGoku.class);
	}

	// Tests de ataques basicos entre personajes
	@Test
	public void testGokuNormalAtacaConElAtaqueBasicoAFreezerNormalYLeDejaLaVidaEn380() throws NoPuedeAtacarAEsaDistanciaException, CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException {
		Personaje goku = new Goku(new Casillero(new Posicion(2, 2)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(2, 3)));

		goku.atacarA(freezer);

		Assert.assertTrue(freezer.getVida() == 380);
	}


	@Test
	public void testGokuNormalAtacaConElAtaqueBasicoAFreezerSegundaFormaYLeDejaLaVidaEn380() throws NoPuedeAtacarAEsaDistanciaException, CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException {
		Personaje goku = new Goku(new Casillero(new Posicion(2, 2)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(2, 3)));
		freezer.transformacion = new SegundaFormaFreezer();

		goku.atacarA(freezer);

		Assert.assertTrue(freezer.getVida() == 384);
	}

	@Test(expected = NoPuedeAtacarAEsaDistanciaException.class)
	public void testGokuNormalAtacaAFreezerNormalEstandoADistancia3LanzaNoPuedeAtacarAEsaDistanciaException() throws NoPuedeAtacarAEsaDistanciaException, CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException {
		Personaje goku = new Goku(new Casillero(new Posicion(2, 2)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(2, 5)));
		goku.atacarA(freezer);

	}

	@Test
	public void testColocoAGohanSS2En33YFreezerNormalEn66YHaceAtaqueBasicoDejandoloEn300() throws NoPuedeAtacarAEsaDistanciaException, CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException {
		// Poder de pelea de Gohan SS2: 100. Poder de pelea de Freezer normal: 20.
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 3)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(6, 6)));
		gohan.transformacion = new SuperSayajin2Gohan();

		gohan.atacarA(freezer);
		Assert.assertTrue(freezer.getVida() == 300);
	}

	@Test
	public void testGokuTieneMenosDel30PorcientoDeVidaEntoncesSuDanioAumenta20Porciento() throws CasilleroOcupadoException, NoPuedeAtacarAEsaDistanciaException, NoPuedeAtacarMismoEquipoException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1))); //danio 20
		Personaje freezer = new Freezer(new Casillero(new Posicion(2, 2))); //danio 20
		goku.reducirVida(400); //baja mas de 30 porciento entonces su danio es 20*1.2 -> 24
		goku.atacarA(freezer); //24 es mayor a 20 entonces el saca 24

		Assert.assertEquals(376, freezer.getVida());
	}

	// Tests de transformaciones especiales

	// Cell

	@Test(expected = NoPuedeTransformarException.class)
	public void testCellIntentaTransformarseSinAbsorber() throws CasilleroOcupadoException, KiInsuficienteException, NoHayProximaTransformacionException, NoPuedeTransformarException {
		// Poder de pelea de Cell Normal = 20. Poder de pelea Piccolo = 20. Danio 20. Vida Cell aumenta 20.
		Personaje cell = new Cell(new Casillero(new Posicion(1, 1)));

		cell.transformar();

	}

	@Test
	public void testCellIntentaTransformarseEnSemiPerfectoAbsorbiendoPreviamente() throws CasilleroOcupadoException, KiInsuficienteException, NoHayProximaTransformacionException, NoPuedeTransformarException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
		// Poder de pelea de Cell Normal = 20. Poder de pelea Piccolo = 20. Danio 20. Vida Cell aumenta 20.
		Personaje cell = new Cell(new Casillero(new Posicion(1, 1)));
		Personaje goku = new Goku(new Casillero(new Posicion(3, 1)));

		for (int i = 1; i <= 4; i++) {
			cell.aumentarKi(5);
			cell.ataqueEspecialA(goku);
		}

		cell.transformar();
		Assert.assertEquals(SemiPerfectoCell.class, cell.transformacion.getClass());
	}

	@Test
	public void testCellIntentaTransformarseEnPerfectoAbsorbiendoPreviamente() throws CasilleroOcupadoException, KiInsuficienteException, NoHayProximaTransformacionException, NoPuedeTransformarException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
		// Poder de pelea de Cell Normal = 20. Poder de pelea Piccolo = 20. Danio 20. Vida Cell aumenta 20.
		Personaje cell = new Cell(new Casillero(new Posicion(1, 1)));
		Personaje goku = new Goku(new Casillero(new Posicion(3, 1)));

		for (int i = 1; i <= 8; i++) {
			cell.aumentarKi(5);
			cell.ataqueEspecialA(goku);
		}

		cell.transformar();
		cell.transformar();
		Assert.assertEquals(PerfectoCell.class, cell.transformacion.getClass());
	}

	// Piccolo
	@Test
	public void testPiccoloSeTransformaAProtectorConVidaDeGohanMenorAUnVeintePorCiento() throws CasilleroOcupadoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(2, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));

		Equipo guerreros = new Equipo("Guerreros Z");
		gohan.unirse(guerreros);
		piccolo.unirse(guerreros);

		gohan.reducirVida(250);

		piccolo.aumentarKi(20);
		piccolo.transformar();
		piccolo.transformar();
		Assert.assertEquals(ProtectorPiccolo.class, piccolo.transformacion.getClass());
	}

	@Test(expected = NoPuedeTransformarException.class)
	public void testPiccoloSeIntentaTransformarAProtectorSinCumplirseCondicionLanzaException() throws CasilleroOcupadoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(2, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));

		Equipo guerreros = new Equipo("Guerreros Z");
		gohan.unirse(guerreros);
		piccolo.unirse(guerreros);

		piccolo.aumentarKi(20);
		piccolo.transformar();
		piccolo.transformar();
	}

	@Test(expected = NoPuedeTransformarException.class)
	public void testPiccoloSeIntentaTransformarAProtectorPeroNoEstaEnElMismoEquipoQueGohanLanzaException() throws CasilleroOcupadoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(2, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));

		Equipo guerreros = new Equipo("Guerreros Z");
		gohan.unirse(guerreros);
		gohan.reducirVida(250);

		piccolo.aumentarKi(20);
		piccolo.transformar();
		piccolo.transformar();
	}

	// Gohan
	@Test
	public void testGohanSeTransformaEnSS2ConVidaDePiccoloYGokuMenorAUnTreintaPorCiento() throws CasilleroOcupadoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(2, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));

		Equipo guerreros = new Equipo("Guerreros Z");
		goku.unirse(guerreros);
		gohan.unirse(guerreros);
		piccolo.unirse(guerreros);

		piccolo.reducirVida(400);
		goku.reducirVida(400);

		gohan.aumentarKi(40);
		gohan.transformar();
		gohan.transformar();
		Assert.assertEquals(SuperSayajin2Gohan.class, gohan.transformacion.getClass());
	}

	@Test(expected = KiInsuficienteException.class)
	public void testGohanIntentaTransformarEnSS2SinKiSuficienteLanzaException() throws CasilleroOcupadoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(2, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));

		Equipo guerreros = new Equipo("Guerreros Z");
		goku.unirse(guerreros);
		gohan.unirse(guerreros);
		piccolo.unirse(guerreros);

		piccolo.reducirVida(400);
		goku.reducirVida(400);

		gohan.aumentarKi(10);
		gohan.transformar();
		gohan.transformar();
	}

	@Test(expected = NoPuedeTransformarException.class)
	public void testGohanSeIntentaTransformarEnSS2SinCumplirseNingunaCondicionLanzaException() throws CasilleroOcupadoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(2, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));

		Equipo guerreros = new Equipo("Guerreros Z");
		goku.unirse(guerreros);
		gohan.unirse(guerreros);
		piccolo.unirse(guerreros);

		gohan.aumentarKi(40);
		gohan.transformar();
		gohan.transformar();
	}

	@Test(expected = NoPuedeTransformarException.class)
	public void testGohanSeIntentaTransformarEnSS2CumpliendoSoloUnaCondicionLanzaException() throws CasilleroOcupadoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(2, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));

		Equipo guerreros = new Equipo("Guerreros Z");
		goku.unirse(guerreros);
		gohan.unirse(guerreros);
		piccolo.unirse(guerreros);

		goku.reducirVida(400);

		gohan.aumentarKi(40);
		gohan.transformar();
		gohan.transformar();
	}

	@Test(expected = NoPuedeTransformarException.class)
	public void testGohanSeIntentaTransformarEnSS2CumpliendoLasCondicionesPeroNoEstanEnMismoEquipoLanzaException() throws CasilleroOcupadoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(2, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));

		Equipo guerreros = new Equipo("Guerreros Z");
		gohan.unirse(guerreros);
		piccolo.unirse(guerreros);

		gohan.aumentarKi(40);
		gohan.transformar();
		gohan.transformar();
	}


	// Tests ataques especiales

	@Test(expected = NoPuedeAtacarMismoEquipoException.class)
	public void testGokuNormalHaceAtaqueEspecialASuEquipoDebeLanzarNoPuedeAtacarMismoEquipoException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(2, 2)));

		goku.ataqueEspecialA(gohan);
	}

	@Test(expected = NoPuedeAtacarAEsaDistanciaException.class)
	public void testGokuNormalHaceAtaqueEspecialAFreezerADistancia3DebeLanzarNoPuedeAtacarAEsaDistanciaException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(4, 1)));
		goku.aumentarKi(20);
		goku.ataqueEspecialA(freezer);
	}

	@Test
	public void testGokuNormalHaceAtaqueEspecialAFreezerNormalLeDebeSacar30DeVidaDejandoloEn370() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		//Poder ataque goku= 20 .. Poder ataque freezer 20. Ataque especial hace 50% mas danio que 20 --> 30
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(3, 1)));
		goku.aumentarKi(20);
		goku.ataqueEspecialA(freezer);

		Assert.assertTrue(freezer.getVida() == 370);
	}

	@Test
	public void testGokuNormalHaceAtaqueEspecialAMajinBooNormalLeDebeSacar24DeVidaDejandoloEn276() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		//Poder ataque goku= 20 .. Poder ataque majin boo 30.(-20% danio) Ataque especial hace 50% mas danio que 20*0.8=16 --> 24
		Personaje goku = new Goku(new Casillero(new Posicion(1, 1)));
		Personaje majinBoo = new MajinBoo(new Casillero(new Posicion(3, 1)));
		goku.aumentarKi(20);
		goku.ataqueEspecialA(majinBoo);

		Assert.assertTrue(majinBoo.getVida() == 276);
	}

	@Test
	public void testFreezerSegundaFormaHaceAtaqueEspecialAPiccoloNormalLeDebeSacar24DeVidaDejandoloEn276() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException, NoHayProximaTransformacionException, NoPuedeTransformarException {
		//Poder ataque freezer 2daforma= 40 .. Poder ataque piccolo 20. Ataque especial hace 50% mas danio que 40 --> 60
		Personaje freezer = new Freezer(new Casillero(new Posicion(1, 1)));
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(3, 1)));
		freezer.aumentarKi(40);
		freezer.transformar();
		freezer.ataqueEspecialA(piccolo);

		Assert.assertTrue(piccolo.getVida() == 440);
	}

	@Test
	public void testCellRealizaAtaqueEspecialAbsorberYSuVidaSeIncrementaLaMismaCantidadDeVidaQuePerdioElAtacado() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		// Poder de pelea de Cell Normal = 20. Poder de pelea Piccolo = 20. Danio 20. Vida Cell aumenta 20.
		Personaje cell = new Cell(new Casillero(new Posicion(1, 1)));
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(2, 2)));
		cell.aumentarKi(5);
		int vidaPreviaCell = cell.getVida();
		cell.ataqueEspecialA(piccolo);

		Assert.assertEquals(20, cell.getVida() - vidaPreviaCell);
	}

	@Test(expected = NoPuedeAtacarMismoEquipoException.class)
	public void testFreezerNormalHaceAtaqueEspecialASuEquipoDebeLanzarNoPuedeAtacarMismoEquipoException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje freezer = new Freezer(new Casillero(new Posicion(1, 1)));
		Personaje cell = new Cell(new Casillero(new Posicion(2, 2)));

		freezer.ataqueEspecialA(cell);
	}
	/*@Test(expected = NoPuedeTransformarException.class)
	public void testPiccoloIntentaTransformarseAProtectorConVidaDeGohanAlMaximo() throws CasilleroOcupadoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Goku goku = new Goku(new Casillero(new Posicion(1,1)));
		Piccolo piccolo = new Piccolo(new Casillero(new Posicion(1,1)));
		Gohan gohan = new Gohan(new Casillero(new Posicion(1,1)));
		EstadoGuerrerosZ estado = new EstadoGuerrerosZ(goku,gohan,piccolo);

		piccolo.aumentarKi(40);
		piccolo.transformar(estado);
		piccolo.transformar(estado);
	}
	@Test
	public void testPiccoloSeTransformaAProtectorConVidaDeGohanMenorAUnTreintaPorCiento() throws CasilleroOcupadoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Goku goku = new Goku(new Casillero(new Posicion(1,1)));
		Piccolo piccolo = new Piccolo(new Casillero(new Posicion(2,1)));
		Gohan gohan = new Gohan(new Casillero(new Posicion(3,1)));

		EstadoGuerrerosZ estado = new EstadoGuerrerosZ(goku,gohan,piccolo);

		gohan.reducirVida(250);

		piccolo.aumentarKi(40);
		piccolo.transformar(estado);
		piccolo.transformar(estado);
		Assert.assertEquals(ProtectorPiccolo.class,piccolo.transformacion.getClass());
	}*/
}
