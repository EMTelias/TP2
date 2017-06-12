package transformacion;


import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.transformacion.*;
import personaje.Personaje;
import tablero.Camino;
import tablero.Casillero;

public abstract class Transformacion {

    protected int velocidad;
    protected int distanciaAtaque;
    protected int poderDePelea;
    protected Transformacion proximaTransformacion;
    protected int kiNecesarioTransformar;

    public Transformacion(int poderDePelea, int distanciaAtaque, int velocidad, Transformacion proxTransformacion, int kiTransformar) {
        this.velocidad = velocidad;
        this.distanciaAtaque = distanciaAtaque;
        this.poderDePelea = poderDePelea;
        this.proximaTransformacion = proxTransformacion;
        this.kiNecesarioTransformar = kiTransformar;
    }


    public Transformacion transformar(Personaje unPersonaje) throws NoHayProximaTransformacionException, NoPuedeTransformarException, KiInsuficienteException {
        int kiActual = unPersonaje.getKi();

        if (kiActual < this.kiNecesarioTransformar) {
            throw new KiInsuficienteException();
        }

        unPersonaje.reducirKi(this.kiNecesarioTransformar);
        return this.proximaTransformacion;
    }


    public Casillero mover(Personaje unPersonaje, Camino camino) throws NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException {

        if (camino.distancia() > this.velocidad ){
            throw new NoPuedeMoverAEsaDistanciaException();
        }

        return camino.recorrerCon(unPersonaje);
    }

    public int getVelocidad() {
        return this.velocidad;
    }

    public int getPoderDePelea() {
        return this.poderDePelea;
    }

    public int getDistanciaAtaque() {
        return this.distanciaAtaque;
    }

}
