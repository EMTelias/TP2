package Consumible;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import Consumibles.Consumible;
import Consumibles.EsferaDelDragon;
import Consumibles.NubeVoladora;
import Consumibles.SemillaDelErmitanio;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import personaje.Freezer;
import personaje.Goku;
import personaje.Personaje;
import tablero.Camino;
import tablero.Casillero;
import tablero.Posicion;

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
	

}
