package partida;

import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import jugador.JugadorEnemigo;
import jugador.JugadorGuerreroZ;
import org.junit.Test;
import org.junit.Assert;
import tablero.Posicion;
import tablero.Tablero;

public class PartidaTest {

    @Test
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
}
