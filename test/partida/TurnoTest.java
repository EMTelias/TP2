package partida;

import equipos.Equipo;
import org.junit.Test;

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
}
