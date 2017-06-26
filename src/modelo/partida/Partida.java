package modelo.partida;

import modelo.excepciones.personaje.*;
import modelo.excepciones.tablero.*;
import modelo.personaje.equipos.Equipo;
import modelo.Consumibles.GeneradorDeConsumibles;
import modelo.Consumibles.GeneradorDeConsumiblesSinActividad;
import modelo.excepciones.JuegoTerminadoException;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.acciones.YaAtacasteEsteTurnoException;
import modelo.excepciones.acciones.YaMovisteEsteTurnoException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.excepciones.transformacion.NoHayProximaTransformacionException;
import modelo.excepciones.transformacion.NoPuedeTransformarException;
import modelo.personaje.*;
import modelo.tablero.Camino;
import modelo.tablero.Posicion;
import modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Partida {
    protected static final int DIM_ALTO = 15;
    protected static final int DIM_ANCHO = 15;

    private Tablero tablero;
    private Equipo equipoGuerrerrosZ = new Equipo("GuerrerosZ");
    private Equipo equipoEnemigos = new Equipo("Enemigos");
    private boolean estadoDePartida;
    private GeneradorDeConsumibles generadorDeConsumibles;
    
    
    private Turno turno;

    protected static Posicion POS_I_GOKU = new Posicion(1,1);
    protected static Posicion POS_I_GOHAN = new Posicion(2,1);
    protected static Posicion POS_I_PICCOLO = new Posicion(1,2);
    protected static Posicion POS_I_CELL = new Posicion(DIM_ANCHO,DIM_ALTO);
    protected static Posicion POS_I_FREEZER = new Posicion(DIM_ANCHO,DIM_ALTO-1);
    protected static Posicion POS_I_MAJINBOO = new Posicion(DIM_ANCHO-1,DIM_ALTO);


    public Partida() throws DimensionDeTableroInvalidoException, CasilleroOcupadoException {

        this.tablero = new Tablero(DIM_ANCHO,DIM_ALTO);
        this.initDeEnemigos();
        this.initDeGuerrerosZ();
        this.estadoDePartida = true;
        this.turno = new Turno(equipoGuerrerrosZ,equipoEnemigos);
        this.generadorDeConsumibles = new GeneradorDeConsumiblesSinActividad();
        
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
        equipoActivo.pasarTurno();
        generadorDeConsumibles.generarConsumibleEn(tablero, DIM_ALTO, DIM_ANCHO);
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
        // supongo que estan todos muertos
        Boolean todosMuertos = true;
        ArrayList<Personaje> personajes = turno.getEquipoEnEspera().getPersonajes();
        for (Personaje personaje : personajes) {
            // si el personaje esta muerto, T and F = F. Si alguno es F, ya no se lanza excepcion luego
            todosMuertos &= personaje.estaMuerto();
        }
        if (todosMuertos || (turno.getEquipoActivo().tieneEsferasNecesariasParaGanar())) {
            throw new JuegoTerminadoException();
        }

    }


    public void ataqueBasico(Personaje atacante, Personaje atacado) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
        if(turno.yaAtaco()){
            throw new YaAtacasteEsteTurnoException();
        }
        if(!esElTurnoDe(atacante)) {
            throw new NoEsSuTurnoException();
        }

        atacante.atacarA(atacado);

        turno.atacar();
        this.revisarVictoria();
        this.revisarFinDelTurno();
    }


    public void ataqueEspecial(Personaje atacante, Personaje atacado) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        if(turno.yaAtaco()){
            throw new YaAtacasteEsteTurnoException();
        }
        if(!esElTurnoDe(atacante)) {
            throw new NoEsSuTurnoException();
        }

        atacante.ataqueEspecialA(atacado);

        turno.atacar();
        this.revisarVictoria();
        this.revisarFinDelTurno();
    }


    public void mover(Personaje personaje, Camino camino) throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException {
        if(turno.yaMovio()) {
            throw new YaMovisteEsteTurnoException();
        }
        if(camino.getPrimerCasillero().distanciaHasta(personaje.getCasillero())>1) {
            throw new HuecoEntreCaminoYPersonajeException();
        }
        if(!esElTurnoDe(personaje)) {
            throw new NoEsSuTurnoException();
        }
        personaje.mover(camino);

        turno.mover();
        revisarVictoria();
        revisarFinDelTurno();
    }


    public void transformar(Personaje personaje) throws NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
        if(!esElTurnoDe(personaje)) {
            throw new NoEsSuTurnoException();
        }
        personaje.transformar();
    }

    private boolean esElTurnoDe(Personaje unPersonaje) {
        return unPersonaje.getEquipo().esSuTurno();
    }

    private void initDeGuerrerosZ() throws CasilleroOcupadoException {

        Personaje goku = new Goku(this.tablero.getCasillero(POS_I_GOKU));
        Personaje gohan = new Gohan(this.tablero.getCasillero(POS_I_GOHAN));
        Personaje piccolo = new Piccolo(this.tablero.getCasillero(POS_I_PICCOLO));

        goku.unirse(this.equipoGuerrerrosZ);
        gohan.unirse(this.equipoGuerrerrosZ);
        piccolo.unirse(this.equipoGuerrerrosZ);
    }

    private void initDeEnemigos() throws CasilleroOcupadoException {

        Personaje cell = new Cell(this.tablero.getCasillero(POS_I_CELL));
        Personaje majinBoo = new MajinBoo(this.tablero.getCasillero(POS_I_MAJINBOO));
        Personaje freezer = new Freezer(this.tablero.getCasillero(POS_I_FREEZER));

        cell.unirse(this.equipoEnemigos);
        majinBoo.unirse(this.equipoEnemigos);
        freezer.unirse(this.equipoEnemigos);

    }

    public void setGeneradorDeConsumibles(GeneradorDeConsumibles unGeneradorDeConsumibles){
    	this.generadorDeConsumibles = unGeneradorDeConsumibles;
    }
    
}

