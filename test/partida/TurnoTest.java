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
        for(int i = 2; i <= 13; i++) {
            casilleros.add(tablero.getCasillero(new Posicion(i,i)));
            partida.moverEnCamino(new Posicion(i-1, i-1), new Camino(casilleros));
            partida.pasar();
            partida.pasar();
            casilleros.clear();
        }

        Assert.assertEquals("GuerrerosZ",partida.turnoActual().getNombre());

        //Aca tengo a goku en el 13,13 y a cell en el 15,15
        partida.atacarEnPosicion(new Posicion(13,13), new Posicion(15,15));

        Assert.assertEquals("GuerrerosZ",partida.turnoActual().getNombre());

        casilleros.add(tablero.getCasillero(new Posicion(14,14)));
        partida.moverEnCamino(new Posicion(13, 13), new Camino(casilleros));

        Assert.assertEquals("Enemigos",partida.turnoActual().getNombre());

    }

    @Test(expected = YaAtacasteEsteTurnoException.class)
    public void testAtaco2vecesEnElMismoTurnoACellYReciboYaAtacasteEsteTurnoException() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, KiInsuficienteException {
        //Para arrancar el test muevo a goku hasta el 13,13
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

        //Aca tengo a goku en el 13,13 y a cell en el 15,15
        partida.atacarEnPosicion(new Posicion(13,13), new Posicion(15,15));
        partida.atacarEnPosicion(new Posicion(13,13), new Posicion(15,15));

    }

    @Test(expected = YaMovisteEsteTurnoException.class)
    public void testMuevo2vecesAGokuEnElMismoTurnoYReciboYaMovisteEsteTurnoException() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, KiInsuficienteException {
        //Para arrancar el test muevo a goku hasta el 13,13
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> casilleros = new ArrayList<>();

        casilleros.add(tablero.getCasillero(new Posicion(1,2)));
        partida.moverEnCamino(new Posicion(1, 1), new Camino(casilleros));
        casilleros.clear();

        casilleros.add(tablero.getCasillero(new Posicion(1,3)));
        partida.moverEnCamino(new Posicion(1, 2), new Camino(casilleros));

    }
}
