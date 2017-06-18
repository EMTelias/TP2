package partida;

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
import personaje.Gohan;
import personaje.Goku;
import personaje.MajinBoo;
import personaje.Personaje;
import tablero.Camino;
import tablero.Casillero;
import tablero.Posicion;
import tablero.Tablero;
import transformacion.goku.KaioKenGoku;

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
    public void testCreoUnaPartidaConDosJugadoresYVerificoQueMajinBooEsteEnLaPosicionx15y14() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Partida partida = new Partida();

        Personaje personajeBuscado = partida.personajeEnPosicion(new Posicion(15,14));
        Assert.assertTrue(personajeBuscado.getClass() == MajinBoo.class);

    }

    @Test
    public void testCreoUnaPartidaYPasoUnaVezEntoncesElKiAumenta5Unidades() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Partida partida = new Partida();
        partida.pasar();
        Personaje goku = partida.getTablero().getCasillero(new Posicion(1,1)).getPersonaje();
        Assert.assertEquals(5, goku.getKi());
    }

    @Test
    public void testCreoUnaPartidaYMuevoAGohanUnaPosicion() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> listaC = new ArrayList<>();
        listaC.add(tablero.getCasillero(new Posicion(1,2)));
        Camino camino = new Camino(listaC);
        partida.moverEnCamino(new Posicion(1,1),camino);

        Assert.assertTrue(Goku.class == partida.personajeEnPosicion(new Posicion(1,2)).getClass());
    }

    @Test
    public void testCreoUnaPartidaYGokuSeMueveEnDireccionDiagonalHastaEstarCercaDeEnemigosYAtaca() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
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
        partida.atacarEnPosicion(new Posicion(13,13), new Posicion(15,15));
        //goku ataca a cell y le genera 20 de danio
        Personaje cell = tablero.getCasillero(new Posicion(15,15)).getPersonaje();
        Assert.assertEquals(480, cell.getVida());

    }

    @Test
    public void testCreoUnaPartidaYGokuSeMueveEnDireccionDiagonalHastaEstarCercaDeEnemigosYUsaAtaqueEspecial() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
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
        partida.ataqueEspecialEnPosicion(new Posicion(13,13), new Posicion(15,15));
        //goku usa ataque especial a cell y le genera 20*1.5 de danio
        Personaje cell = tablero.getCasillero(new Posicion(15,15)).getPersonaje();
        Assert.assertEquals(470, cell.getVida());

    }

    @Test
    public void testCreoUnaPartidaYTransformoAGokuLuegoDe5TurnosEntoncesSuVelocidadAumenta1() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeTransformarException, NoHayProximaTransformacionException, KiInsuficienteException {
        Partida partida = new Partida();
        for(int i = 1; i <=4; i++) {
            partida.pasar(); // paso mi turno
            partida.pasar(); // mi oponente pasa su turno
        }
        Personaje goku = partida.getTablero().getCasillero(new Posicion(1,1)).getPersonaje();
        int velocidadPrevia = goku.getVelocidad();
        partida.transformarPersonaje(new Posicion(1,1));
        Assert.assertEquals(1, goku.getVelocidad() - velocidadPrevia);
    }

    @Test(expected = KiInsuficienteException.class)
    public void testCreoUnaPartidaYTransformoAGokuLuegoDe3TurnosLanzaException() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeTransformarException, NoHayProximaTransformacionException, KiInsuficienteException {
        Partida partida = new Partida();
        for(int i = 1; i <=2; i++) {
            partida.pasar(); // paso mi turno
            partida.pasar(); // mi oponente pasa su turno
        }
        partida.transformarPersonaje(new Posicion(1,1));

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
