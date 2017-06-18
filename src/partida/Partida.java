package partida;

import equipos.Equipo;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.CasillaSinPersonajeException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CaminoInvalidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import excepciones.transformacion.KiInsuficienteException;
import excepciones.transformacion.NoHayProximaTransformacionException;
import excepciones.transformacion.NoPuedeTransformarException;
import personaje.*;
import tablero.Camino;
import tablero.Posicion;
import tablero.Tablero;


public class Partida {
    private static final int DIM_ALTO = 15;
    private static final int DIM_ANCHO = 15;
    private static final int AUMENTO_KI = 5;

    private Tablero tablero;
    private Equipo equipoGuerrerrosZ = new Equipo("GuerrerosZ");
    private Equipo equipoEnemigos = new Equipo("Enemigos");
    private boolean estadoDePartida;

    private Turno turno;


    public Partida() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException {

        this.tablero = new Tablero(DIM_ANCHO,DIM_ALTO);
        this.initDeEnemigos();
        this.initDeGuerrerosZ();
        this.turno = new Turno(equipoGuerrerrosZ,equipoEnemigos);
        this.estadoDePartida = true;

    }
    public void pasar() {
        Equipo equipoActivo = turno.getEquipoActivo();
        turno.pasar();
        equipoActivo.aumentarKi(AUMENTO_KI);
        equipoActivo.revisarTransformadosEnChocolate();
    }

    public Equipo turnoActual() {
        return this.turno.getEquipoActivo();
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Personaje personajeEnPosicion(Posicion posicion) {
       Personaje p =  tablero.getCasillero(posicion).getPersonaje();
       if (p == null){
           throw new CasillaSinPersonajeException();
       }
       return p;
    }

    public void atacarEnPosicion(Posicion posAtacante, Posicion posAtacado) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
        Personaje atacante = this.personajeEnPosicion(posAtacante);
        Personaje atacado = this.personajeEnPosicion(posAtacado);
        atacante.atacarA(atacado);
    }

    public void ataqueEspecialEnPosicion(Posicion posAtacante, Posicion posAtacado) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        Personaje atacante = this.personajeEnPosicion(posAtacante);
        Personaje atacado = this.personajeEnPosicion(posAtacado);
        atacante.ataqueEspecialA(atacado);
    }

    public void moverEnCamino(Posicion posPersonaje, Camino camino) throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException {
        Personaje personaje = this.personajeEnPosicion(posPersonaje);
        personaje.mover(camino);
    }

    public void transformarPersonaje(Posicion posPersonaje) throws NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
        Personaje personaje = this.personajeEnPosicion(posPersonaje);
        personaje.transformar();
    }

    private void initDeGuerrerosZ() throws CasilleroOcupadoException {
        //Creo los guerreros Z
        Posicion pos1 = new Posicion(1,1);
        Posicion pos2 = new Posicion(2,1);
        Posicion pos3 = new Posicion(3,1);

        Personaje goku = new Goku(this.tablero.getCasillero(pos1));
        Personaje gohan = new Gohan(this.tablero.getCasillero(pos2));
        Personaje piccolo = new Piccolo(this.tablero.getCasillero(pos3));

        goku.unirse(this.equipoGuerrerrosZ);
        gohan.unirse(this.equipoGuerrerrosZ);
        piccolo.unirse(this.equipoGuerrerrosZ);
    }

    private void initDeEnemigos() throws CasilleroOcupadoException {
        //Creo los enemigos
        Posicion pos1 = new Posicion(DIM_ANCHO,this.DIM_ALTO);
        Posicion pos2 = new Posicion(DIM_ANCHO,this.DIM_ALTO-1);
        Posicion pos3 = new Posicion(DIM_ANCHO,this.DIM_ALTO-2);

        Personaje cell = new Cell(this.tablero.getCasillero(pos1));
        Personaje majinBoo = new MajinBoo(this.tablero.getCasillero(pos2));
        Personaje freezer = new Freezer(this.tablero.getCasillero(pos3));

        cell.unirse(this.equipoEnemigos);
        majinBoo.unirse(this.equipoEnemigos);
        freezer.unirse(this.equipoEnemigos);

    }
}

