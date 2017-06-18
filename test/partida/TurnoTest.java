package partida;

import equipos.Equipo;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import org.junit.Assert;
import org.junit.Test;
import personaje.Cell;
import personaje.Goku;
import personaje.Personaje;
import tablero.Camino;
import tablero.Casillero;
import tablero.Posicion;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TurnoTest {

    @Test
    public void testPrimerTurnoEsElPrimerJugador(){
        Equipo guerreroZ = new Equipo("GuerrerosZ");
        Equipo enemigos = new Equipo("Enemigos");
        Turno turno = new Turno(guerreroZ,enemigos);
        assertEquals(guerreroZ,turno.getEquipoActivo());
    }
    @Test
    public void testPasoTurnoJugadorDosEsElJugadorEnMovimiento(){
        Equipo guerreroZ = new Equipo("GuerrerosZ");
        Equipo enemigos = new Equipo("Enemigos");
        Turno turno = new Turno(guerreroZ,enemigos);
        turno.pasar();
        assertEquals(enemigos,turno.getEquipoActivo());
    }

    @Test
    public void testMuevoAGokuYAtacoACellYYaNoEsMasElTurnoDeLosGuerrerosZ() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
        Partida partida = new Partida();
        Posicion posicion1 = new Posicion(1, 1);
        Posicion posicion2 = new Posicion(1, 2);
        Posicion posicion3 = new Posicion(1, 5);
        Personaje cell = new Cell(new Casillero(posicion3));
        partida.getTablero().colocar(cell,posicion3);

        ArrayList<Casillero> listaC = new ArrayList<>();
        listaC.add(partida.getTablero().getCasillero(new Posicion(1,2)));
        Camino camino = new Camino(listaC);

        Assert.assertEquals("GuerrerosZ",partida.turnoActual().getNombre());
        partida.moverEnCamino(posicion1,camino);
        Assert.assertEquals("GuerrerosZ",partida.turnoActual().getNombre());
        partida.atacarEnPosicion(posicion2,posicion2);
        Assert.assertEquals("Enemigos",partida.turnoActual().getNombre());

    }
}
