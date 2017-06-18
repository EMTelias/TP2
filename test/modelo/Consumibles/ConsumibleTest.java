package modelo.Consumibles;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import modelo.Consumibles.Consumible;
import modelo.Consumibles.EsferaDelDragon;
import modelo.Consumibles.NubeVoladora;
import modelo.Consumibles.SemillaDelErmitanio;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.excepciones.tablero.DimensionDeTableroInvalidoException;
import modelo.personaje.Freezer;
import modelo.personaje.Goku;
import modelo.personaje.Personaje;
import modelo.personaje.Piccolo;
import modelo.tablero.Camino;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;

public class ConsumibleTest {
	
	@Test
	public void testGokuEnModoNormalConsumeNubeVoladoraYPuedeMover4PosicionesNoLanzaExcepcionn() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException {
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
		Camino camino = new Camino(casilleros);
		
		Consumible nubeVoladora = new NubeVoladora();
		Personaje goku = new Goku(casillero1);
		goku.consumir(nubeVoladora);
		
		goku.mover(camino);
		Assert.assertTrue(casillero1.estaVacio());
		Assert.assertFalse(casillero5.estaVacio());
	}

	@Test 
	public void testGokuEnModoNormalConsumeEsferaDelDragonyLeProduceAFreezer25Danio() throws CasilleroOcupadoException, NoPuedeAtacarAEsaDistanciaException, NoPuedeAtacarMismoEquipoException{
		Casillero casilleroGoku = new Casillero(new Posicion(1,1));
		Casillero casilleroFreezer = new Casillero(new Posicion(1,2));
		
		Personaje goku = new Goku(casilleroGoku);
		Personaje freezer = new Freezer(casilleroFreezer);
		
		Consumible esferaDelDragon = new EsferaDelDragon();
		
		goku.consumir(esferaDelDragon);
		goku.atacarA(freezer);
		Assert.assertEquals(freezer.getVida(), 375);
	}

	@Test
	public void testGokuRegenera100DeVidaAlConsumirSemillaDelErmitanio() throws CasilleroOcupadoException{
		
		Casillero casilleroGoku = new Casillero (new Posicion(1,1));
		Personaje goku = new Goku(casilleroGoku);
		Consumible semillaDelErmitanio = new SemillaDelErmitanio();
		
		goku.reducirVida(150);
		goku.consumir(semillaDelErmitanio);
		
		Assert.assertEquals(goku.getVida(),450);
	}
	
	@Test 
	public void testGokuConsumeSemillaDelErmitanioConMenosDe100DeDanioSeRegeneraSoloHastaSuVidaOriginal() throws CasilleroOcupadoException{
		Casillero casilleroGoku = new Casillero (new Posicion(1,1));
		Personaje goku = new Goku(casilleroGoku);
		Consumible semillaDelErmitanio = new SemillaDelErmitanio();
		
		goku.reducirVida(50);
		goku.consumir(semillaDelErmitanio);
		
		Assert.assertEquals(goku.getVida(),500);
	}
	
	@Test (expected = CasilleroOcupadoException.class)
	public void testCreoUnaEsferaDelDragonEnUnCasilleroOcupadoDevuelveCasilleroOcupadoException() throws CasilleroOcupadoException{
		Casillero unCasillero = new Casillero(new Posicion(2,2));
		Personaje goku = new Goku(unCasillero);
		new EsferaDelDragon(unCasillero);
	}
	
	@Test (expected = CasilleroOcupadoException.class)
	public void testCreoDosEsferasEnElMismoCasilleroDevuelveCasilleroOcupadoException() throws CasilleroOcupadoException{
		Casillero unCasillero = new Casillero(new Posicion(2,2));
		new EsferaDelDragon(unCasillero);
		new EsferaDelDragon(unCasillero);
	}
	
	@Test
	public void testGokuConsumeEsferaDelDragonYHace25DeDanioPor2TurnosEnElTerceroProduce20() throws CasilleroOcupadoException, NoPuedeAtacarAEsaDistanciaException, NoPuedeAtacarMismoEquipoException{
		// vida de freezer = 400
		// dos ataques con esfera de goku = 50 danio (25 cada uno)
		//tercer ataque sin esfera = 20 danio
		// vida resultante de freezer 400 - 70 = 330

		Casillero casilleroGoku = new Casillero(new Posicion(2,2));
		Casillero casilleroFreezer = new Casillero(new Posicion(2,3));
		
		Personaje goku = new Goku(casilleroGoku);
		Personaje freezer = new Freezer(casilleroFreezer);
		
		Consumible esfera = new EsferaDelDragon();
		goku.consumir(esfera);
		goku.atacarA(freezer);
		goku.pasarTurno();
		goku.atacarA(freezer);
		goku.pasarTurno();
		goku.atacarA(freezer);
		
		Assert.assertEquals(freezer.getVida(), 330);
	}
	
	@Test (expected = NoPuedeMoverAEsaDistanciaException.class)
	public void testGokuConsumeNubeVoladoraPor2TurnosPuedeMover4PosicionesLaTerceraVezQueQuiereMover4PosicionesDevuelveNoPuedeMoverAEsaDistanciaException() throws CaminoInvalidoException, CasilleroOcupadoException, NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException{
		Casillero casilleroGoku = new Casillero(new Posicion(1,1));
		
		Casillero casillero12 = new Casillero(new Posicion(1,2));
		Casillero casillero13 = new Casillero(new Posicion(1,3));
		Casillero casillero14 = new Casillero(new Posicion(1,4));
		Casillero casillero15 = new Casillero(new Posicion(1,5));
		ArrayList<Casillero> casillerosCamino1 = new ArrayList<Casillero>();
		casillerosCamino1.add(casillero12);
		casillerosCamino1.add(casillero13);
		casillerosCamino1.add(casillero14);
		casillerosCamino1.add(casillero15);
		Camino camino1 = new Camino(casillerosCamino1);
		
		Casillero casillero16 = new Casillero(new Posicion(1,6));
		Casillero casillero17 = new Casillero(new Posicion(1,7));
		Casillero casillero18 = new Casillero(new Posicion(1,8));
		Casillero casillero19 = new Casillero(new Posicion(1,9));
		ArrayList<Casillero> casillerosCamino2 = new ArrayList<Casillero>();
		casillerosCamino2.add(casillero16);
		casillerosCamino2.add(casillero17);
		casillerosCamino2.add(casillero18);
		casillerosCamino2.add(casillero19);
		Camino camino2 = new Camino(casillerosCamino2);

		Casillero casillero110 = new Casillero(new Posicion(1,10));
		Casillero casillero111 = new Casillero(new Posicion(1,11));
		Casillero casillero112 = new Casillero(new Posicion(1,12));
		Casillero casillero113 = new Casillero(new Posicion(1,13));
		ArrayList<Casillero> casillerosCamino3 = new ArrayList<Casillero>();
		casillerosCamino3.add(casillero110);
		casillerosCamino3.add(casillero111);
		casillerosCamino3.add(casillero112);
		casillerosCamino3.add(casillero113);
		Camino camino3 = new Camino(casillerosCamino3);

		Personaje goku = new Goku(casilleroGoku);
		Consumible nubeVoladora = new NubeVoladora();
		goku.consumir(nubeVoladora);
		
		goku.mover(camino1);
		goku.pasarTurno();
		goku.mover(camino2);
		goku.pasarTurno();
		goku.mover(camino3);
	}
	
	@Test
	public void testGokuCapturaLaNubeAlMoverseAlCasilleroQueLaContieneYMueve4PosicionesNoDevuelveExcepcion() throws CaminoInvalidoException, CasilleroOcupadoException, NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException{
		Casillero casillero11 = new Casillero(new Posicion(1,1));
		
		Casillero casillero12 = new Casillero(new Posicion(1,2));
		ArrayList<Casillero> casillerosCamino1 = new ArrayList<Casillero>();
		casillerosCamino1.add(casillero12);
		Camino caminoPrimerMovimiento = new Camino(casillerosCamino1);

		Casillero casillero13 = new Casillero(new Posicion(1,3));
		Casillero casillero14 = new Casillero(new Posicion(1,4));
		Casillero casillero15 = new Casillero(new Posicion(1,5));
		Casillero casillero16 = new Casillero(new Posicion(1,6));
		ArrayList<Casillero> casillerosCamino2 = new ArrayList<Casillero>();
		casillerosCamino2.add(casillero13);
		casillerosCamino2.add(casillero14);
		casillerosCamino2.add(casillero15);
		casillerosCamino2.add(casillero16);
		Camino caminoSegundoMovimientoDeGokuConNube = new Camino(casillerosCamino2);
		
		Consumible nubeVoladora = new NubeVoladora(casillero12);
		Personaje goku = new Goku(casillero11);
		
		goku.mover(caminoPrimerMovimiento);
		goku.mover(caminoSegundoMovimientoDeGokuConNube);
	}
	
	
	@Test (expected = NoPuedeMoverAEsaDistanciaException.class)
	public void testGokuAlPosicionarseEnElCasilleroDeLaNubeVoladoraLaCapturaMueveYElSiguientePersonajeQuePasaPorEseCasilleroNoCapturaLaNubeYAlMoverDevuelveNoPuedeMoverAEsaDistanciaException() throws CaminoInvalidoException, CasilleroOcupadoException, NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException{
		Casillero casillero11 = new Casillero(new Posicion(1,1));
		Casillero casillero22 = new Casillero(new Posicion(2,2));
		
		Casillero casillero12 = new Casillero(new Posicion(1,2));
		ArrayList<Casillero> casillerosCamino1 = new ArrayList<Casillero>();
		casillerosCamino1.add(casillero12);
		Camino caminoPrimerMovimiento = new Camino(casillerosCamino1);
		
		Casillero casillero13 = new Casillero(new Posicion(1,3));
		Casillero casillero14 = new Casillero(new Posicion(1,4));
		Casillero casillero15 = new Casillero(new Posicion(1,5));
		Casillero casillero16 = new Casillero(new Posicion(1,6));
		ArrayList<Casillero> casillerosCamino2 = new ArrayList<Casillero>();
		casillerosCamino2.add(casillero13);
		casillerosCamino2.add(casillero14);
		casillerosCamino2.add(casillero15);
		casillerosCamino2.add(casillero16);
		Camino caminoSegundoMovimientoDeGokuConNube = new Camino(casillerosCamino2);
		
		ArrayList<Casillero> casillerosCamino3 = new ArrayList<Casillero>();
		casillerosCamino3.add(casillero13);
		casillerosCamino3.add(casillero14);
		casillerosCamino3.add(casillero15);
		Camino caminoSegundoMovimientoDePiccolo = new Camino(casillerosCamino3);
		
		Consumible nubeVoladora = new NubeVoladora(casillero12);
		Personaje goku = new Goku(casillero11);
		Personaje piccolo = new Piccolo(casillero22);
		
		goku.mover(caminoPrimerMovimiento);
		goku.mover(caminoSegundoMovimientoDeGokuConNube);
		
		piccolo.mover(caminoSegundoMovimientoDePiccolo);
	}
	
	
	
}
