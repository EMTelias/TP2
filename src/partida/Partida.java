package partida;

import equipo.Equipo;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.CasillaSinPersonajeException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.CasilleroOcupadoException;
import excepciones.tablero.DimensionDeTableroInvalidoException;
import excepciones.transformacion.KiInsuficienteException;
import excepciones.transformacion.NoHayProximaTransformacionException;
import excepciones.transformacion.NoPuedeTransformarException;
import personaje.Personaje;
import tablero.Camino;
import tablero.Posicion;
import tablero.Tablero;


public class Partida {
    private static final int DIM_ALTO = 20;
    private static final int DIM_ANCHO = 20;

    private Tablero tablero;

    private boolean estadoDePartida;

    private Turno turno;


    public Partida(Equipo equipoGuerrerosZ, Equipo equipoEnemigo) throws DimensionDeTableroInvalidoException, CasilleroOcupadoException {

        this.tablero = new Tablero(DIM_ANCHO,DIM_ALTO);
        this.tablero.initDeGuerrerosZ(equipoGuerrerosZ);
        this.tablero.initDeEnemigos(equipoEnemigo);

        this.turno = new Turno(equipoGuerrerosZ,equipoEnemigo);
        this.estadoDePartida = true;

    }
    public void pasar() {
        this.turno.pasar();
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
       }else{
           return p;
       }
    }

    public void atacarEnPosicion(Posicion posAtacante, Posicion posAtacado) throws NoPuedeAtacarMismoEquipoException, NoPuedeAtacarAEsaDistanciaException {
        Personaje atacante = this.personajeEnPosicion(posAtacante);
        Personaje atacado = this.personajeEnPosicion(posAtacado);
        atacante.atacarA(atacado);
    }
    public void moverEnCamino(Posicion posPersonaje, Camino camino) throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException {
        Personaje personaje = this.personajeEnPosicion(posPersonaje);
        personaje.mover(camino);
    }
    public void transformarPersonaje(Posicion posPersonaje) throws NoPuedeTransformarException, KiInsuficienteException, NoHayProximaTransformacionException {
        Personaje personaje = this.personajeEnPosicion(posPersonaje);
        personaje.transformar();
    }

}

