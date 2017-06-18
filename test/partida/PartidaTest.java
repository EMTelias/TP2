package partida;

import equipo.Equipo;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import org.junit.Assert;
import org.junit.Test;
import personaje.Gohan;
import personaje.MajinBoo;
import personaje.Personaje;
import tablero.Posicion;

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
        Equipo guerreroZ = new Equipo("GuerrerosZ");
        Equipo enemigos = new Equipo("Enemigos");
        Partida partida = new Partida(guerreroZ,enemigos);

        Assert.assertTrue(guerreroZ.getTurno());
        Assert.assertFalse(enemigos.getTurno());

    }

    @Test
    public void testCreoUnaPartidaConDosEquiposPasoTurnoEntoncesEsElTurnoDeEnemigos() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Equipo guerreroZ = new Equipo("GuerrerosZ");
        Equipo enemigos = new Equipo("Enemigos");
        Partida partida = new Partida(guerreroZ,enemigos);

        partida.pasar();
        Assert.assertFalse(guerreroZ.getTurno());
        Assert.assertTrue(enemigos.getTurno());
    }

    @Test
    public void testCreoUnaPartidaConDosEquiposPidoElEquipoQueDebeJugarEntoncesEsElTurnoDelQueDebeJugar() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Equipo guerreroZ = new Equipo("GuerrerosZ");
        Equipo enemigos = new Equipo("Enemigos");
        Partida partida = new Partida(guerreroZ,enemigos);

        Equipo equipo = partida.turnoActual();
        Assert.assertTrue(equipo.getTurno());
    }

    @Test
    public void testCreoUnaPartidaConDosJugadoresYVerificoQueGohanEsteEnLaPosicionx2y1() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Equipo guerreroZ = new Equipo("GuerrerosZ");
        Equipo enemigos = new Equipo("Enemigos");
        Partida partida = new Partida(guerreroZ,enemigos);

        Personaje personajeBuscado = partida.personajeEnPosicion(new Posicion(2,1));
        Assert.assertTrue(personajeBuscado.getClass() == Gohan.class);

    }

    @Test
    public void testCreoUnaPartidaConDosJugadoresYVerificoQueMajinBooEsteEnLaPosicionx20y19() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Equipo guerreroZ = new Equipo("GuerrerosZ");
        Equipo enemigos = new Equipo("Enemigos");
        Partida partida = new Partida(guerreroZ,enemigos);

        Personaje personajeBuscado = partida.personajeEnPosicion(new Posicion(20,19));
        Assert.assertTrue(personajeBuscado.getClass() == MajinBoo.class);

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
