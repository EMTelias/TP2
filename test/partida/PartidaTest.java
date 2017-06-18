package partida;

import excepciones.JuegoTerminadoException;
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
        Posicion nuevaPosGoku = new Posicion(2,2);
        listaC.add(tablero.getCasillero(nuevaPosGoku));
        Camino camino = new Camino(listaC);
        partida.moverEnCamino(Partida.POS_I_GOKU,camino);

        Assert.assertTrue(Goku.class == partida.personajeEnPosicion(nuevaPosGoku).getClass());
    }

    @Test
    public void testCreoUnaPartidaYGokuSeMueveEnDireccionDiagonalHastaEstarCercaDeEnemigosYAtaca() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
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
        //goku ataca a cell y le genera 20 de danio
        Personaje cell = tablero.getCasillero(Partida.POS_I_CELL).getPersonaje();
        Assert.assertEquals(480, cell.getVida());

    }

    @Test
    public void testCreoUnaPartidaYGokuSeMueveEnDireccionDiagonalHastaEstarCercaDeEnemigosYUsaAtaqueEspecial() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
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
        partida.ataqueEspecialEnPosicion(nuevaPosGoku, Partida.POS_I_CELL);
        //goku usa ataque especial a cell y le genera 20*1.5 de danio
        Personaje cell = tablero.getCasillero(Partida.POS_I_CELL).getPersonaje();
        Assert.assertEquals(470, cell.getVida());

    }

    @Test
    public void testCreoUnaPartidaYTransformoAGokuLuegoDe5TurnosEntoncesSuVelocidadAumenta1() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeTransformarException, NoHayProximaTransformacionException, KiInsuficienteException {
        Partida partida = new Partida();
        for(int i = 1; i <=4; i++) {
            partida.pasar(); // paso mi turno
            partida.pasar(); // mi oponente pasa su turno
        }
        Personaje goku = partida.getTablero().getCasillero(Partida.POS_I_GOKU).getPersonaje();
        int velocidadPrevia = goku.getVelocidad();
        partida.transformarPersonaje(Partida.POS_I_GOKU);
        Assert.assertEquals(1, goku.getVelocidad() - velocidadPrevia);
    }

    @Test(expected = KiInsuficienteException.class)
    public void testCreoUnaPartidaYTransformoAGokuLuegoDe3TurnosLanzaException() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException, NoPuedeTransformarException, NoHayProximaTransformacionException, KiInsuficienteException {
        Partida partida = new Partida();
        for(int i = 1; i <=2; i++) {
            partida.pasar(); // paso mi turno
            partida.pasar(); // mi oponente pasa su turno
        }
        partida.transformarPersonaje(Partida.POS_I_GOKU);

    }

    @Test(expected = JuegoTerminadoException.class)
    public void testEliminoA2EnemigosYGokuMataAlUltimoReciboGanasteException() throws CasilleroOcupadoException, NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, DimensionDeTableroInvalidoException, CaminoInvalidoException, NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
        //Para arrancar el test muevo a goku hasta el 13,13
        Partida partida = new Partida();
        Tablero tablero = partida.getTablero();
        ArrayList<Casillero> casilleros = new ArrayList<>();
        // Goku se mueve en forma diagonal hasta Pos(13,13) si DIM tablero es 15x15
        for(int i = 2; i <= (Partida.DIM_ALTO-2); i++) {
            casilleros.add(tablero.getCasillero(new Posicion(i, i)));
            partida.moverEnCamino(new Posicion(i - 1, i - 1), new Camino(casilleros));
            partida.pasar();
            partida.pasar();
            casilleros.clear();
        }
        Posicion nuevaPosGoku = new Posicion(Partida.DIM_ANCHO - 2,Partida.DIM_ALTO - 2);
        //Mato a los otros 2 enemigos y dejo a cell en 1 de vida(15,15)
        partida.personajeEnPosicion(Partida.POS_I_CELL).reducirVida(499);
        partida.personajeEnPosicion(Partida.POS_I_MAJINBOO).reducirVida(300);
        partida.personajeEnPosicion(Partida.POS_I_FREEZER).reducirVida(400);

        partida.atacarEnPosicion(nuevaPosGoku, Partida.POS_I_CELL);
 
    }

}
