package modelo.partida;

import modelo.excepciones.JuegoTerminadoException;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.CasilleroSinPersonajeException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import modelo.excepciones.tablero.CaminoInvalidoException;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.excepciones.tablero.DimensionDeTableroInvalidoException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.excepciones.transformacion.NoPuedeTransformarException;
import org.junit.Assert;
import org.junit.Test;
import modelo.personaje.Gohan;
import modelo.personaje.Goku;
import modelo.personaje.MajinBoo;
import modelo.personaje.Personaje;
import modelo.tablero.Camino;
import modelo.tablero.Casillero;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

import java.util.ArrayList;

public class PartidaTest {


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
        Personaje personajeBuscado = partida.personajeEnPosicion(Partida.POS_I_GOHAN);

        Assert.assertTrue(personajeBuscado.getClass() == Gohan.class);

    }

    @Test
    public void testCreoUnaPartidaConDosJugadoresYVerificoQueMajinBooEsteEnLaPosicionx14y15() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        Partida partida = new Partida();

        Personaje personajeBuscado = partida.personajeEnPosicion(Partida.POS_I_MAJINBOO);
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
    public void testCreoUnaPartidaYMuevoAGokuUnaPosicion() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> listaC = new ArrayList<>();
        // Goku esta en Posicion(1,1). Lo muevo a Posicion(2,2)
        Personaje goku = partida.personajeEnPosicion(Partida.POS_I_GOKU);
        Posicion nuevaPosGoku = new Posicion(2,2);
        listaC.add(tablero.getCasillero(nuevaPosGoku));
        Camino camino = new Camino(listaC);
        partida.mover(goku,camino);

        Assert.assertTrue(Goku.class == partida.personajeEnPosicion(nuevaPosGoku).getClass());
    }

    @Test
    public void testCreoUnaPartidaYGokuSeMueveEnDireccionDiagonalHastaEstarCercaDeEnemigosYAtaca() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> casilleros = new ArrayList<>();
        // Goku se mueve en forma diagonal hasta Pos(13,13) si DIM modelo.tablero es 15x15
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
        //goku ataca a cell y le genera 20 de danio
        Assert.assertEquals(480, cell.getVida());

    }

    @Test
    public void testCreoUnaPartidaYGokuSeMueveEnDireccionDiagonalHastaEstarCercaDeEnemigosYUsaAtaqueEspecial() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> casilleros = new ArrayList<>();
        // Goku se mueve en forma diagonal hasta Pos(13,13) si DIM modelo.tablero es 15x15
        Personaje goku = partida.personajeEnPosicion(Partida.POS_I_GOKU);
        Personaje cell = partida.personajeEnPosicion(Partida.POS_I_CELL);
        for(int i = 2; i <= (Partida.DIM_ALTO-2); i++) {
            casilleros.add(tablero.getCasillero(new Posicion(i,i)));
            partida.mover(goku, new Camino(casilleros));
            partida.pasar();
            partida.pasar();
            casilleros.clear();
        }
        partida.ataqueEspecial(goku, cell);
        //goku usa ataque especial a cell y le genera 20*1.5 de danio
        Assert.assertEquals(470, cell.getVida());

    }

    @Test
    public void testCreoUnaPartidaYTransformoAGokuLuegoDe5TurnosEntoncesSuVelocidadAumenta1() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeTransformarException, NoHayProximaTransformacionException, KiInsuficienteException {
        Partida partida = new Partida();
        for(int i = 1; i <=4; i++) {
            partida.pasar(); // paso mi turno
            partida.pasar(); // mi oponente pasa su turno
        }
        Personaje goku = partida.personajeEnPosicion(Partida.POS_I_GOKU);
        int velocidadPrevia = goku.getVelocidad();
        partida.transformar(goku);
        Assert.assertEquals(1, goku.getVelocidad() - velocidadPrevia);
    }

    @Test(expected = KiInsuficienteException.class)
    public void testCreoUnaPartidaYTransformoAGokuLuegoDe3TurnosLanzaException() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeTransformarException, NoHayProximaTransformacionException, KiInsuficienteException {
        Partida partida = new Partida();
        for(int i = 1; i <=2; i++) {
            partida.pasar(); // paso mi turno
            partida.pasar(); // mi oponente pasa su turno
        }
        Personaje goku = partida.personajeEnPosicion(Partida.POS_I_GOKU);
        partida.transformar(goku);

    }

    @Test(expected = JuegoTerminadoException.class)
    public void testEliminoA2EnemigosYGokuMataAlUltimoReciboGanasteException() {
        //Para arrancar el test muevo a goku hasta el 13,13
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> casilleros = new ArrayList<>();
        // Goku se mueve en forma diagonal hasta Pos(13,13) si DIM modelo.tablero es 15x15
        Personaje goku = partida.personajeEnPosicion(Partida.POS_I_GOKU);
        Personaje cell = partida.personajeEnPosicion(Partida.POS_I_CELL);
        for(int i = 2; i <= (Partida.DIM_ALTO-2); i++) {
            casilleros.add(tablero.getCasillero(new Posicion(i, i)));
            partida.mover(goku, new Camino(casilleros));
            partida.pasar();
            partida.pasar();
            casilleros.clear();
        }
        //Mato a los otros 2 enemigos y dejo a cell en 1 de vida(15,15)
        partida.personajeEnPosicion(Partida.POS_I_CELL).reducirVida(499);
        partida.personajeEnPosicion(Partida.POS_I_MAJINBOO).reducirVida(300);
        partida.personajeEnPosicion(Partida.POS_I_FREEZER).reducirVida(400);

        partida.ataqueBasico(goku, cell);
 
    }

    @Test (expected = CasilleroSinPersonajeException.class)
    public void testCreoPartidaYBuscoPersonajeEnCasilleroSinPersonajeLanzaExcepcion() {
        Partida partida = new Partida();
        partida.personajeEnPosicion(new Posicion(5,5));
    }

}
