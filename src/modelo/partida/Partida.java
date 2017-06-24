package modelo.partida;

import modelo.excepciones.tablero.*;
import modelo.personaje.equipos.Equipo;
import modelo.Consumibles.GeneradorDeConsumibles;
import modelo.Consumibles.GeneradorDeConsumiblesSinActividad;
import modelo.excepciones.JuegoTerminadoException;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.acciones.YaAtacasteEsteTurnoException;
import modelo.excepciones.acciones.YaMovisteEsteTurnoException;
import modelo.excepciones.personaje.CasillaSinPersonajeException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
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
    private static final int AUMENTO_KI = 5;

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

    public void ataqueBasico(Personaje atacante, Personaje atacado) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
        if(turno.yaAtaco()){ throw new YaAtacasteEsteTurnoException(); }

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

    public void ataqueEspecial(Personaje atacante, Personaje atacado) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException, KiInsuficienteException {
        if(turno.yaAtaco()){ throw new YaAtacasteEsteTurnoException(); }

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

    public void mover(Personaje personaje, Camino camino) throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, CaminoInvalidoException {
        if(turno.yaMovio()){ throw new YaMovisteEsteTurnoException(); }
        if(camino.getPrimerCasillero().distanciaHasta(personaje.getCasillero())>1){ throw new HuecoEntreCaminoYPersonajeException(); }

        personaje.mover(camino);

        turno.mover();
        revisarFinDelTurno();
    }

    public void transformarPersonaje(Posicion posPersonaje) throws NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
        Personaje personaje = this.personajeEnPosicion(posPersonaje);
        personaje.transformar();
    }

    public void transformar(Personaje personaje) throws NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
        personaje.transformar();
    }

    private void initDeGuerrerosZ() throws CasilleroOcupadoException {
        //Creo los guerreros Z
        /*Posicion pos1 = new Posicion(1,1);
        Posicion pos2 = new Posicion(2,1);
        Posicion pos3 = new Posicion(3,1);*/

        Personaje goku = new Goku(this.tablero.getCasillero(POS_I_GOKU));
        Personaje gohan = new Gohan(this.tablero.getCasillero(POS_I_GOHAN));
        Personaje piccolo = new Piccolo(this.tablero.getCasillero(POS_I_PICCOLO));

        goku.unirse(this.equipoGuerrerrosZ);
        gohan.unirse(this.equipoGuerrerrosZ);
        piccolo.unirse(this.equipoGuerrerrosZ);
    }

    private void initDeEnemigos() throws CasilleroOcupadoException {
        //Creo los enemigos
        /*Posicion pos1 = new Posicion(DIM_ANCHO,this.DIM_ALTO);
        Posicion pos2 = new Posicion(DIM_ANCHO,this.DIM_ALTO-1);
        Posicion pos3 = new Posicion(DIM_ANCHO,this.DIM_ALTO-2);*/

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

