package modelo.partida;

import modelo.personaje.Personaje;
import modelo.personaje.equipos.Equipo;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.acciones.YaAtacasteEsteTurnoException;
import modelo.excepciones.acciones.YaMovisteEsteTurnoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.excepciones.tablero.DimensionDeTableroInvalidoException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import org.junit.Assert;
import org.junit.Test;
import modelo.tablero.Camino;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

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
        Personaje goku = partida.personajeEnPosicion(Partida.POS_I_GOKU);
        Personaje cell = partida.personajeEnPosicion(Partida.POS_I_CELL);
        for(int i = 2; i <= (Partida.DIM_ALTO-2); i++) {
            casilleros.add(tablero.getCasillero(new Posicion(i,i)));
            partida.mover(goku, new Camino(casilleros));
            partida.pasar();
            partida.pasar();
            casilleros.clear();
        }

        Assert.assertEquals("GuerrerosZ",partida.turnoActual().getNombre());

        partida.ataqueBasico(goku, cell);

        Assert.assertEquals("GuerrerosZ",partida.turnoActual().getNombre());

        casilleros.add(tablero.getCasillero(new Posicion(Partida.DIM_ANCHO - 1,Partida.DIM_ALTO - 1)));
        partida.mover(goku, new Camino(casilleros));

        Assert.assertEquals("Enemigos",partida.turnoActual().getNombre());

    }

    @Test(expected = YaAtacasteEsteTurnoException.class)
    public void testAtaco2vecesEnElMismoTurnoACellYReciboYaAtacasteEsteTurnoException() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, KiInsuficienteException {
        //Para arrancar el test muevo a goku hasta el 13,13
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> casilleros = new ArrayList<>();
        // Goku se mueve en forma diagonal hasta Pos(13,13) si DIM tablero es 15x15
        Personaje goku = partida.personajeEnPosicion(Partida.POS_I_GOKU);
        Personaje cell = partida.personajeEnPosicion(Partida.POS_I_CELL);
        for(int i = 2; i <= (Partida.DIM_ALTO-2); i++) {
            casilleros.add(tablero.getCasillero(new Posicion(i,i)));
            partida.mover(goku, new Camino(casilleros));
            partida.pasar();
            partida.pasar();
            casilleros.clear();
        }
        partida.ataqueBasico(goku, cell);
        partida.ataqueBasico(goku, cell);

    }

    @Test(expected = YaMovisteEsteTurnoException.class)
    public void testMuevo2vecesAGokuEnElMismoTurnoYReciboYaMovisteEsteTurnoException() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, KiInsuficienteException {
        //Para arrancar el test muevo a goku hasta el 13,13
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> casilleros = new ArrayList<>();

        Personaje goku = partida.personajeEnPosicion(Partida.POS_I_GOKU);
        Posicion nuevaPosGoku = new Posicion(2,2);
        casilleros.add(tablero.getCasillero(nuevaPosGoku));
        partida.mover(goku, new Camino(casilleros));
        casilleros.clear();

        // Intento mover de nuevo a goku en diagonal
        casilleros.add(tablero.getCasillero(new Posicion(3,3)));
        partida.mover(goku, new Camino(casilleros));

    }
}
