package transformacion;

import acciones.AtaqueEspecialHandler;
import excepciones.acciones.*;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import excepciones.transformacion.KiInsuficienteException;
import personaje.Personaje;
import tablero.Camino;

public class Chocolate extends Transformacion {

    Transformacion transformacionOriginal;
    int turnosTransformado;

    public Chocolate(Transformacion transformacionActual) {
        turnosTransformado = 0;
        transformacionOriginal = transformacionActual;
    }

    @Override
    public void atacarA(Personaje PersonajeAtacante, Personaje PersonajeDefensor) throws NoPuedeAtacarAEsaDistanciaException {
        throw new NoPuedeAtacarSiendoChocolateException();
    }

    @Override
    public Transformacion transformacionOriginal() {
        return transformacionOriginal;
    }

    @Override
    public void ataqueEspecialA(Personaje personajeAtacante, Personaje personajeAtacado) throws NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
        throw new NoPuedeUsarAtaqueEspecialSiendoChocolateException();
    }

    @Override
    public Transformacion transformar(Personaje unPersonaje){
        throw new NoPuedeTransformarseSiendoChocolateException();
    }

    @Override
    public void mover(Personaje unPersonaje, Camino camino){
        throw new NoPuedeMoverseSiendoChocolateException();
    }

    @Override
    public Transformacion revisarTransformacionChocolate(){
        if(turnosTransformado > 1){
            return transformacionOriginal;
        }else{
            turnosTransformado++;
            return this;
        }
    }

    @Override
    public boolean esChocolate(){return true;}

    @Override
    public int aumentarKi(int aumento){return 0;}

    public void pasarTurno(){}
}
