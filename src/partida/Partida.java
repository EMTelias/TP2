package partida;

import equipos.Equipo;
import excepciones.JuegoTerminadoException;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.acciones.YaAtacasteEsteTurnoException;
import excepciones.acciones.YaMovisteEsteTurnoException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Partida {
    private static final int DIM_ALTO = 15;
    private static final int DIM_ANCHO = 15;
    private static final int AUMENTO_KI = 5;

    Posicion POS_GOKU = new Posicion(0,0);
    Posicion POS_GOHAN = new Posicion(0,7);
    Posicion POS_PICCOLO = new Posicion(0,14);

    Posicion POS_CELL = new Posicion(14,7);
    Posicion POS_FREEZER = new Posicion(14,14);
    Posicion POS_MAJIN = new Posicion(14,0);

    private Tablero tablero;
    private Equipo equipoGuerrerrosZ = new Equipo("GuerrerosZ");
    private Equipo equipoEnemigos = new Equipo("Enemigos");
    private boolean estadoDePartida;

    private Turno turno;


    public Partida() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException {

        this.tablero = new Tablero(DIM_ANCHO,DIM_ALTO);
        this.initDeEnemigos();
        this.initDeGuerrerosZ();
        this.estadoDePartida = true;
        this.turno = new Turno(equipoGuerrerrosZ,equipoEnemigos);

    }

    public void setearPrimerJugadorRandom(){
        //Elijo un equipo al azar para que sea el primero en jugar.
        //(este metodo NO puede ir en el constructor ya que haria random los tests ya creados)
        List<Equipo> equipos = new ArrayList<Equipo>();
        equipos.add(equipoGuerrerrosZ);
        equipos.add(equipoEnemigos);
        Equipo primerEquipo = equipos.remove(new Random().nextInt(equipos.size()));
        Equipo segundoEquipo = equipos.remove(0);
        this.turno = new Turno(primerEquipo,segundoEquipo);
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

    private void revisarFinDelTurno(){
        if(turno.yaAtaco() && turno.yaMovio()){
            this.pasar();
        }
    }

    private void revisarVictoria(){
        if(turno.getEquipoEnEspera().getPersonajes().size() == 0){
                throw new JuegoTerminadoException();
            }
    }

    public void atacarEnPosicion(Posicion posAtacante, Posicion posAtacado) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
        if(turno.yaAtaco()){ throw new YaAtacasteEsteTurnoException(); }

        Personaje atacante = this.personajeEnPosicion(posAtacante);
        Personaje atacado = this.personajeEnPosicion(posAtacado);
        atacante.atacarA(atacado);

        turno.atacar();
        this.revisarVictoria();
        this.revisarFinDelTurno();
    }

    public void ataqueEspecialEnPosicion(Posicion posAtacante, Posicion posAtacado) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        if(turno.yaAtaco()){ throw new YaAtacasteEsteTurnoException(); }

        Personaje atacante = this.personajeEnPosicion(posAtacante);
        Personaje atacado = this.personajeEnPosicion(posAtacado);
        atacante.ataqueEspecialA(atacado);

        turno.atacar();
        this.revisarVictoria();
        this.revisarFinDelTurno();
    }

    public void moverEnCamino(Posicion posPersonaje, Camino camino) throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException {
        if(turno.yaMovio()){ throw new YaMovisteEsteTurnoException(); }

        Personaje personaje = this.personajeEnPosicion(posPersonaje);
        personaje.mover(camino);

        turno.mover();
        revisarFinDelTurno();
    }

    public void transformarPersonaje(Posicion posPersonaje) throws NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
        Personaje personaje = this.personajeEnPosicion(posPersonaje);
        personaje.transformar();
    }

    private void initDeGuerrerosZ() throws CasilleroOcupadoException {
        //Creo los guerreros Z
        Personaje goku = new Goku(this.tablero.getCasillero(POS_GOKU));
        Personaje gohan = new Gohan(this.tablero.getCasillero(POS_GOHAN));
        Personaje piccolo = new Piccolo(this.tablero.getCasillero(POS_PICCOLO));

        goku.unirse(this.equipoGuerrerrosZ);
        gohan.unirse(this.equipoGuerrerrosZ);
        piccolo.unirse(this.equipoGuerrerrosZ);
    }

    private void initDeEnemigos() throws CasilleroOcupadoException {
        //Creo los enemigos
        Personaje cell = new Cell(this.tablero.getCasillero(POS_CELL));
        Personaje majinBoo = new MajinBoo(this.tablero.getCasillero(POS_MAJIN));
        Personaje freezer = new Freezer(this.tablero.getCasillero(POS_FREEZER));

        cell.unirse(this.equipoEnemigos);
        majinBoo.unirse(this.equipoEnemigos);
        freezer.unirse(this.equipoEnemigos);

    }

}

