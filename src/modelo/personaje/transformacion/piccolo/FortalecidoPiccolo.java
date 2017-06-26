package modelo.personaje.transformacion.piccolo;


import modelo.personaje.equipos.Equipo;
import modelo.excepciones.transformacion.NoPuedeTransformarException;
import modelo.personaje.Gohan;
import modelo.personaje.Personaje;
import modelo.personaje.transformacion.Transformacion;

import java.util.ArrayList;

public class FortalecidoPiccolo extends Transformacion{
    protected static final int poderDePelea = 40;
    protected static final int distanciaAtaque = 4;
    protected static final int velocidad = 3;
    protected static final Transformacion proximaTransformacion = new ProtectorPiccolo();
    protected static final int kiNecesarioTransformar = 0;
    protected static final double multVidaMinimaGohan = 0.2;

    public FortalecidoPiccolo() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }


    public Transformacion transformar(Personaje personaje) {
        if (!this.puedeTransformar(personaje)) {
            throw new NoPuedeTransformarException();
        }
        return proximaTransformacion;
    }


    private boolean puedeTransformar(Personaje personaje) {
        Gohan gohan;
        try{
            Equipo amigos = personaje.getEquipo();
            gohan = this.buscarGohan(amigos);
        }catch(NullPointerException e) {
            return false;
        }

        if (gohan == null) {
            return false;
        }
        return gohan.necesitaAyudaDePiccolo();
    }


    private Gohan buscarGohan(Equipo equipo) {
        ArrayList<Personaje> personajesEquipo = equipo.getPersonajes();
        Gohan gohan = null;
        for(Personaje personajeLista : personajesEquipo) {
            if (personajeLista.getClass() == Gohan.class) {
                gohan = (Gohan)personajeLista;
            }
        }
        return gohan;
    }
}
