package partida;

import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import org.junit.Assert;
import org.junit.Test;
import personaje.Gohan;
import personaje.Goku;
import personaje.MajinBoo;
import personaje.Personaje;
import tablero.Camino;
import tablero.Casillero;
import tablero.Posicion;
import tablero.Tablero;

import java.util.ArrayList;

public class PartidaTest {

 /*   @Test
    public void testCreoUnaPartidaConDosJugadoresCadaUnoConSus3PersonajesEntoncesLosPersonajesEstanInicializadosEnLosExtremos() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException {

        JugadorGuerreroZ jugador1 = new JugadorGuerreroZ("jugador1");
        JugadorEnemigo jugador2 = new JugadorEnemigo("jugador2");
        Partida partida = new Partida(jugador1, jugador2);
        Tablero tablero = partida.getTablero();

        Assert.assertFalse(tablero.estaVacioEn(new Posicion(1,1)));
        Assert.assertFalse(tablero.estaVacioEn(new Posicion(2,1)));
        Assert.assertFalse(tablero.estaVacioEn(new Posicion(3,1)));
        Assert.assertFalse(tablero.estaVacioEn(new Posicion(20,18)));
        Assert.assertFalse(tablero.estaVacioEn(new Posicion(20,19)));
        Assert.assertFalse(tablero.estaVacioEn(new Posicion(20,20)));
    }
*/

    @Test
    public void testCreoUnaPartidaConDosEquiposEntoncesEsElTurnoDeGuerrerosZ() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Partida partida = new Partida();

        Assert.assertEquals("GuerrerosZ",partida.turnoActual().getNombre());
    }

    @Test
    public void testCreoUnaPartidaConDosEquiposPasoTurnoEntoncesEsElTurnoDeEnemigos() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Partida partida = new Partida();
        partida.pasar();

        Assert.assertEquals("Enemigos",partida.turnoActual().getNombre());
    }

    @Test
    public void testCreoUnaPartidaConDosJugadoresYVerificoQueGohanEsteEnLaPosicionx2y1() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Partida partida = new Partida();
        Personaje personajeBuscado = partida.personajeEnPosicion(new Posicion(2,1));

        Assert.assertTrue(personajeBuscado.getClass() == Gohan.class);

    }

    @Test
    public void testCreoUnaPartidaConDosJugadoresYVerificoQueMajinBooEsteEnLaPosicionx20y19() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Partida partida = new Partida();

        Personaje personajeBuscado = partida.personajeEnPosicion(new Posicion(20,19));
        Assert.assertTrue(personajeBuscado.getClass() == MajinBoo.class);

    }
    @Test
    public void testCreoUnaPartidaYMuevoAGohanUnaPosicion() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> listaC = new ArrayList();
        listaC.add(tablero.getCasillero(new Posicion(1,2)));
        Camino camino = new Camino(listaC);
        partida.moverEnCamino(new Posicion(1,1),camino);

        Assert.assertTrue(Goku.class == partida.personajeEnPosicion(new Posicion(1,2)).getClass());
    }


    /*@Test
    public void testCreoUnaPartidaYMuevoAGoku2HaciaAbajo() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        JugadorGuerreroZ jugador1 = new JugadorGuerreroZ("jugador1");
        JugadorEnemigo jugador2 = new JugadorEnemigo("jugador2");
        Partida partida = new Partida(jugador1, jugador2);
        partida.moverPersonaje();
        Assert.assertTrue(personajeBuscado.getClass() == MajinBoo.class);

    }*/
}
