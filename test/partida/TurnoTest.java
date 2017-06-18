package partida;

import equipos.Equipo;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.acciones.YaAtacasteEsteTurnoException;
import excepciones.acciones.YaMovisteEsteTurnoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import excepciones.transformacion.KiInsuficienteException;
import org.junit.Assert;
import org.junit.Test;
import personaje.Cell;
import personaje.Goku;
import personaje.Personaje;
import tablero.Camino;
import tablero.Casillero;
import tablero.Posicion;
import tablero.Tablero;

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
    public void testMuevoAGokuYAtacoACellYYaNoEsMasElTurnoDeLosGuerrerosZ() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, KiInsuficienteException {
        //Para arrancar el test muevo a goku hasta el 13,13
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> casilleros = new ArrayList<>();
        // Goku se mueve en forma diagonal hasta Pos(13,13) si DIM tablero es 15x15
        for(int i = 2; i <= (Partida.DIM_ALTO-2); i++) {
            casilleros.add(tablero.getCasillero(new Posicion(i,i)));
            partida.moverEnCamino(new Posicion(i-1, i-1), new Camino(casilleros));
            partida.pasar();
            partida.pasar();
            casilleros.clear();
        }

        Assert.assertEquals("GuerrerosZ",partida.turnoActual().getNombre());

        Posicion nuevaPosGoku = new Posicion(Partida.DIM_ANCHO - 2,Partida.DIM_ALTO - 2);
        partida.atacarEnPosicion(nuevaPosGoku, Partida.POS_I_CELL);

        Assert.assertEquals("GuerrerosZ",partida.turnoActual().getNombre());

        casilleros.add(tablero.getCasillero(new Posicion(Partida.DIM_ANCHO - 1,Partida.DIM_ALTO - 1)));
        partida.moverEnCamino(nuevaPosGoku, new Camino(casilleros));

        Assert.assertEquals("Enemigos",partida.turnoActual().getNombre());

    }

    @Test(expected = YaAtacasteEsteTurnoException.class)
    public void testAtaco2vecesEnElMismoTurnoACellYReciboYaAtacasteEsteTurnoException() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, KiInsuficienteException {
        //Para arrancar el test muevo a goku hasta el 13,13
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> casilleros = new ArrayList<>();
        // Goku se mueve en forma diagonal hasta Pos(13,13) si DIM tablero es 15x15
        for(int i = 2; i <= (Partida.DIM_ALTO-2); i++) {
            casilleros.add(tablero.getCasillero(new Posicion(i,i)));
            partida.moverEnCamino(new Posicion(i-1, i-1), new Camino(casilleros));
            partida.pasar();
            partida.pasar();
            casilleros.clear();
        }

        Posicion nuevaPosGoku = new Posicion(Partida.DIM_ANCHO - 2,Partida.DIM_ALTO - 2);
        partida.atacarEnPosicion(nuevaPosGoku, Partida.POS_I_CELL);
        partida.atacarEnPosicion(nuevaPosGoku, Partida.POS_I_CELL);

    }

    @Test(expected = YaMovisteEsteTurnoException.class)
    public void testMuevo2vecesAGokuEnElMismoTurnoYReciboYaMovisteEsteTurnoException() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, KiInsuficienteException {
        //Para arrancar el test muevo a goku hasta el 13,13
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> casilleros = new ArrayList<>();

        Posicion nuevaPosGoku = new Posicion(2,2);
        casilleros.add(tablero.getCasillero(nuevaPosGoku));
        partida.moverEnCamino(Partida.POS_I_GOKU, new Camino(casilleros));
        casilleros.clear();

        // Intento mover de nuevo a goku en diagonal
        casilleros.add(tablero.getCasillero(new Posicion(3,3)));
        partida.moverEnCamino(nuevaPosGoku, new Camino(casilleros));

    }
}
