package Consumible;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import Consumibles.Consumible;
import Consumibles.NubeVoladora;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
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
}
