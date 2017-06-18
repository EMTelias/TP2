package modelo.personaje;

import modelo.personaje.equipos.Equipo;
import modelo.excepciones.acciones.*;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.excepciones.tablero.DimensionDeTableroInvalidoException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.excepciones.transformacion.NoPuedeTransformarException;
import org.junit.Assert;
import org.junit.Test;
import modelo.partida.Partida;
import modelo.tablero.Camino;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;
import modelo.personaje.transformacion.Chocolate;
import modelo.personaje.transformacion.cell.PerfectoCell;
import modelo.personaje.transformacion.cell.SemiPerfectoCell;
import modelo.personaje.transformacion.freezer.SegundaFormaFreezer;
import modelo.personaje.transformacion.gohan.SuperSayajin2Gohan;
import modelo.personaje.transformacion.goku.NormalGoku;
import modelo.personaje.transformacion.goku.SuperSayajinGoku;
import modelo.personaje.transformacion.piccolo.ProtectorPiccolo;

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


	//Tests de modelo.personaje.transformacion por ki de los personajes

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
		Assert.assertTrue(goku.transformacion.getClass() == modelo.personaje.transformacion.goku.KaioKenGoku.class);
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
		cell.reducirVida(100);
		int vidaPreviaCell = cell.getVida();
		cell.ataqueEspecialA(piccolo);

		Assert.assertEquals(20, cell.getVida() - vidaPreviaCell);
	}

	@Test
	public void testMajinBooHaceAtaqueEspecialAPiccoloNormalYLoTransformaEnChocolate() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException, NoHayProximaTransformacionException, NoPuedeTransformarException {
		//Poder ataque freezer 2daforma= 40 .. Poder ataque piccolo 20. Ataque especial hace 50% mas danio que 40 --> 60
		Personaje majinBoo = new MajinBoo(new Casillero(new Posicion(1, 1)));
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(3, 1)));
		majinBoo.aumentarKi(40);
		majinBoo.ataqueEspecialA(piccolo);

		Assert.assertEquals(Chocolate.class, piccolo.transformacion.getClass());
	}

	@Test(expected = NoPuedeMoverseSiendoChocolateException.class)
	public void testMajinBooHaceAtaqueEspecialAGokuNormalYLoTransformaEnChocolateEntoncesGokuNoSePuedeMover() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException, NoHayProximaTransformacionException, NoPuedeTransformarException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
		//Poder ataque freezer 2daforma= 40 .. Poder ataque piccolo 20. Ataque especial hace 50% mas danio que 40 --> 60
		Casillero casillero31 = new Casillero(new Posicion(3, 1));
		Personaje majinBoo = new MajinBoo(new Casillero(new Posicion(1, 1)));
		Personaje goku = new Goku(casillero31);
		majinBoo.aumentarKi(40);
		majinBoo.ataqueEspecialA(goku);

		Casillero casillero41 = new Casillero(new Posicion(4,1));
		ArrayList<Casillero> casilleros = new ArrayList<>();
		casilleros.add(casillero31);
		casilleros.add(casillero41);
		Camino camino = new Camino(casilleros);
		goku.mover(camino);
	}

	@Test(expected = NoPuedeAtacarSiendoChocolateException.class)
	public void testMajinBooHaceAtaqueEspecialAGohanNormalYLoTransformaEnChocolateEntoncesGohanNoPuedeAtacar() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException, NoHayProximaTransformacionException, NoPuedeTransformarException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
		Personaje majinBoo = new MajinBoo(new Casillero(new Posicion(1, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));
		majinBoo.aumentarKi(40);
		majinBoo.ataqueEspecialA(gohan);

		gohan.atacarA(majinBoo);
	}

	@Test(expected = NoPuedeUsarAtaqueEspecialSiendoChocolateException.class)
	public void testMajinBooHaceAtaqueEspecialAGohanNormalYLoTransformaEnChocolateEntoncesGohanNoPuedeUsarAtaqueEspecial() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException, NoHayProximaTransformacionException, NoPuedeTransformarException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
		Personaje majinBoo = new MajinBoo(new Casillero(new Posicion(1, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));
		majinBoo.aumentarKi(40);
		majinBoo.ataqueEspecialA(gohan);

		gohan.ataqueEspecialA(majinBoo);
	}

	@Test(expected = NoPuedeTransformarseSiendoChocolateException.class)
	public void testMajinBooHaceAtaqueEspecialAGohanNormalYLoTransformaEnChocolateEntoncesGohanNoPuedeTransformarse() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException, NoHayProximaTransformacionException, NoPuedeTransformarException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
		Personaje majinBoo = new MajinBoo(new Casillero(new Posicion(1, 1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(3, 1)));
		majinBoo.aumentarKi(40);
		majinBoo.ataqueEspecialA(gohan);

		gohan.transformar();
	}

	@Test(expected = NoPuedeAtacarMismoEquipoException.class)
	public void testFreezerNormalHaceAtaqueEspecialASuEquipoDebeLanzarNoPuedeAtacarMismoEquipoException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje freezer = new Freezer(new Casillero(new Posicion(1, 1)));
		Personaje cell = new Cell(new Casillero(new Posicion(2, 2)));

		freezer.ataqueEspecialA(cell);
	}


	@Test(expected = KiInsuficienteException.class)
	public void testGokuNormalHaceAtaqueEspecialSinKiSuficienteLanzaKiInsuficienteException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(1,2)));
		goku.ataqueEspecialA(freezer);
	}

	@Test(expected = KiInsuficienteException.class)
	public void testGohanNormalHaceAtaqueEspecialSinKiSuficienteLanzaKiInsuficienteException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje gohan = new Gohan(new Casillero(new Posicion(1,1)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(1,2)));

		gohan.reducirVida(250);
		gohan.ataqueEspecialA(freezer);
	}

	@Test(expected = KiInsuficienteException.class)
	public void testPiccoloNormalHaceAtaqueEspecialSinKiSuficienteLanzaKiInsuficienteException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(1,1)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(1,2)));

		piccolo.ataqueEspecialA(freezer);
	}

	@Test(expected = KiInsuficienteException.class)
	public void testCellNormalHaceAtaqueEspecialSinKiSuficienteLanzaKiInsuficienteException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(1,1)));
		Personaje cell = new Cell(new Casillero(new Posicion(1,2)));

		cell.ataqueEspecialA(piccolo);
	}

	@Test(expected = KiInsuficienteException.class)
	public void testFreezerNormalHaceAtaqueEspecialSinKiSuficienteLanzaKiInsuficienteException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(1,1)));
		Personaje freezer = new Freezer(new Casillero(new Posicion(1,2)));

		freezer.ataqueEspecialA(piccolo);
	}

	@Test(expected = KiInsuficienteException.class)
	public void testMajinBooNormalHaceAtaqueEspecialSinKiSuficienteLanzaKiInsuficienteException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje piccolo = new Piccolo(new Casillero(new Posicion(1,1)));
		Personaje majinBoo = new MajinBoo(new Casillero(new Posicion(1,2)));

		majinBoo.ataqueEspecialA(piccolo);
	}

	@Test(expected = NoPuedeAtacarMismoEquipoException.class)
	public void testGokuNormalHaceAtaqueEspecialAGohanPeroSonDelMismoEquipoDebeLanzarException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(1,2)));

		goku.ataqueEspecialA(gohan);
	}

	@Test(expected = NoPuedeAtacarSiendoChocolateException.class)
	public void testConviertoAGokuEnChocolateYAtacaRecibiendoExcepcion() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(1,1)));
		goku.convertirseEnChocolate();
		goku.atacarA(gohan);
	}

	@Test
	public void testConviertoAGokuEnChocolateYRevisoQueSeTransformo() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		goku.convertirseEnChocolate();
		Assert.assertTrue(goku.transformacion.getClass() == Chocolate.class);
	}

	@Test
	public void testConviertoAGokuEnChocolateLuegoLoDeConviertoYRevisoQueSuTransformacionEstaBien() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		Personaje gohan = new Gohan(new Casillero(new Posicion(1,1)));
		goku.convertirseEnChocolate();
		goku.dejarDeSerChocolate();
		Assert.assertTrue(goku.transformacion.getClass() == NormalGoku.class);
	}

	@Test(expected = NoPuedeUsarAtaqueEspecialSiendoChocolateException.class)
	public void testConviertoAGokuEnChocolaYUsaAtaqueEspecialACellRecibeExcepcion() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		Personaje cell = new Cell(new Casillero(new Posicion(1,1)));
		goku.convertirseEnChocolate();
		goku.ataqueEspecialA(cell);
	}

	@Test(expected = NoPuedeTransformarseSiendoChocolateException.class)
	public void testConviertoAGokuEnChocolateYloTransformoReciboExcepcion() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException, NoHayProximaTransformacionException, NoPuedeTransformarException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		goku.convertirseEnChocolate();
		goku.transformar();
	}

	@Test(expected = NoPuedeMoverseSiendoChocolateException.class)
	public void testConviertoAGokuEnChocolateYLoMuevoReciboExcepcion() throws CaminoInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
		Personaje goku = new Goku(new Casillero(new Posicion(1,1)));
		goku.convertirseEnChocolate();
		Casillero casillero1 = new Casillero(new Posicion(2, 1));
		Casillero casillero2 = new Casillero(new Posicion(3, 1));
		ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
		casilleros.add(casillero1);
		casilleros.add(casillero2);
		Camino camino = new Camino(casilleros);
		goku.mover(camino);

	}

	@Test
	public void testConviertoEnChocolateAGokuYEn2TurnosMiosSigueSiendoChocolate() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
		Partida partida = new Partida();

		Personaje goku = partida.personajeEnPosicion(new Posicion(1,1));
		goku.convertirseEnChocolate();
		int turno=0;
		for(int i=0;i<4;i++){
			//System.out.println("turno: "+turno+" - "+goku.modelo.personaje.transformacion);
			partida.pasar();
			turno++;
		}
		Assert.assertTrue(goku.transformacion.getClass() == Chocolate.class);
	}

	@Test
	public void testConviertoEnChocolateAGokuYEn3TurnosMiosDespierta() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
		Partida partida = new Partida();

		Personaje goku = partida.personajeEnPosicion(new Posicion(1,1));
		goku.convertirseEnChocolate();
		int turno=0;
		for(int i=0;i<6;i++){
			//System.out.println("turno: "+turno+" - "+goku.modelo.personaje.transformacion);
			partida.pasar();
			turno++;
		}
		Assert.assertTrue(goku.transformacion.getClass() == NormalGoku.class);
	}

	@Test
	public void testMajinBooTransformaEnChocolateAGokuYPor3TurnosDeGokuEsChocolate() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException, NoHayProximaTransformacionException, NoPuedeTransformarException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, DimensionDeTableroInvalidoException {
		Partida partida = new Partida();
		partida.pasar();//Simulo estar en el turno de majinBoo

		Personaje majinBoo = new MajinBoo(new Casillero(new Posicion(1, 1)));
		Personaje goku = partida.personajeEnPosicion(new Posicion(1,1));

		//System.out.println(goku.modelo.personaje.transformacion);

		majinBoo.aumentarKi(40);
		majinBoo.ataqueEspecialA(goku);

		int turno = 0;
		for(int i=0;i<7;i++){
			//System.out.println("turno: "+turno+" - "+goku.modelo.personaje.transformacion);
			partida.pasar();
			turno++;
		}
		Assert.assertTrue(goku.transformacion.getClass() == NormalGoku.class);

	}

	@Test
	public void testGokuCon20DeKiEn5TurnosDeElTendra45DeKi() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
		Partida partida = new Partida();
		Personaje goku = partida.personajeEnPosicion(new Posicion(1, 1));
		goku.aumentarKi(20);
		for (int i = 1; i <= 10; i++) {//Yo paso 5 veces, el enemigo pasa 5 veces.. (5 inicios de turno)
			partida.pasar();
		}
		Assert.assertEquals(goku.ki,45);
	}

	@Test
	public void testMajinBooTransformaEnChocolateAGokuQuienTiene50DeKiYRecienEnSuCuartoTurnoTendria55DeKi() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException, NoHayProximaTransformacionException, NoPuedeTransformarException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, DimensionDeTableroInvalidoException {
		Partida partida = new Partida();
		Personaje majinBoo = new MajinBoo(new Casillero(new Posicion(1, 1)));
		Personaje goku = partida.personajeEnPosicion(new Posicion(1, 1));
		goku.aumentarKi(50);
		partida.pasar();//Ahora estoy en el turno de majinBoo..

		majinBoo.aumentarKi(40);
		majinBoo.ataqueEspecialA(goku);

		//int turno = 1;
		for (int i = 1; i < 8; i++) {
			//System.out.println("turno: " + turno + " - " + goku.modelo.personaje.transformacion);
			//System.out.println(goku.ki);
			partida.pasar();
			//turno++;
		}
		Assert.assertTrue(goku.ki == 55);

	}

	@Test
	public void testGokuAtacaACellCon1DeVidaMatandoloYAhoraNoHayNadieEnEseCasillero() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
		//Para arrancar el test muevo a goku hasta el 13,13
		Partida partida = new Partida();
		Tablero tablero = partida.getTablero();
		ArrayList<Casillero> casilleros = new ArrayList<>();
		for(int i = 2; i <= 13; i++) {
			casilleros.add(tablero.getCasillero(new Posicion(i,i)));
			partida.moverEnCamino(new Posicion(i-1, i-1), new Camino(casilleros));
			partida.pasar();
			partida.pasar();
			casilleros.clear();
		}

		//Aca tengo a goku en el 13,13 y a cell en el 15,15
		partida.personajeEnPosicion(new Posicion(15,15)).reducirVida(499);//Lo dejo en 1
		Assert.assertTrue(!partida.getTablero().estaVacioEn(new Posicion(15,15)));
		partida.atacarEnPosicion(new Posicion(13,13), new Posicion(15,15));//Lo mato
		Assert.assertTrue(partida.getTablero().estaVacioEn(new Posicion(15,15)));

	}
}
