package transformacion.gohan;


import equipos.Equipo;
import excepciones.transformacion.KiInsuficienteException;
import excepciones.transformacion.NoPuedeTransformarException;
import personaje.Gohan;
import personaje.Goku;
import personaje.Personaje;
import personaje.Piccolo;
import transformacion.Transformacion;

import java.util.ArrayList;

public class SuperSayajin1Gohan extends Transformacion{
    protected static final int poderDePelea = 30;
    protected static final int distanciaAtaque = 2;
    protected static final int velocidad = 2;
    protected static final Transformacion proximaTransformacion = new SuperSayajin2Gohan();
    protected static final int kiNecesarioTransformar = 30;

    public SuperSayajin1Gohan() {
        super(poderDePelea, distanciaAtaque, velocidad, proximaTransformacion, kiNecesarioTransformar);
    }

    public Transformacion transformar(Personaje personaje) throws NoPuedeTransformarException, KiInsuficienteException {
        if (!this.puedeTransformar(personaje)) {
            throw new NoPuedeTransformarException();
        }
        if (!personaje.tieneSuficienteKi(kiNecesarioTransformar)) {
            throw new KiInsuficienteException();
        }
        personaje.reducirKi(kiNecesarioTransformar);
        return proximaTransformacion;
    }


    private boolean puedeTransformar(Personaje personaje) {
        Equipo amigos = personaje.getEquipo();
        Goku goku = this.buscarGoku(amigos);
        Piccolo piccolo = this.buscarPiccolo(amigos);
        if (goku == null || piccolo == null) {
            return false;
        }
        return goku.necesitaAyudaDeGohan() && piccolo.necesitaAyudaDeGohan();
    }


    private Goku buscarGoku(Equipo equipo) {
        ArrayList<Personaje> personajesEquipo = equipo.getPersonajes();
        Goku goku = null;
        for(Personaje personajeLista : personajesEquipo) {
            if (personajeLista.getClass() == Goku.class) {
                goku = (Goku)personajeLista;
            }
        }
        return goku;
    }

    private Piccolo buscarPiccolo(Equipo equipo) {
        ArrayList<Personaje> personajesEquipo = equipo.getPersonajes();
        Piccolo piccolo = null;
        for(Personaje personajeLista : personajesEquipo) {
            if (personajeLista.getClass() == Piccolo.class) {
                piccolo = (Piccolo)personajeLista;
            }
        }
        return piccolo;
    }
}
