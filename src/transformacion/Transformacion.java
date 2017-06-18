package transformacion;


import Consumibles.Efecto;
import Consumibles.SinEfectoEspecial;
import acciones.Ataque;
import acciones.AtaqueEspecialHandler;
import excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
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
    protected Efecto efecto;
    
    public Transformacion(int poderDePelea, int distanciaAtaque, int velocidad, Transformacion proxTransformacion, int kiTransformar) {
        this.velocidad = velocidad;
        this.distanciaAtaque = distanciaAtaque;
        this.poderDePelea = poderDePelea;
        this.proximaTransformacion = proxTransformacion;
        this.kiNecesarioTransformar = kiTransformar;
        this.efecto = new SinEfectoEspecial();
   }

    protected Transformacion() {
    }


    public Transformacion transformar(Personaje unPersonaje) throws NoHayProximaTransformacionException, NoPuedeTransformarException, KiInsuficienteException {
        int kiActual = unPersonaje.getKi();

        if (kiActual < this.kiNecesarioTransformar) {
            throw new KiInsuficienteException();
        }

        unPersonaje.reducirKi(this.kiNecesarioTransformar);
        return this.proximaTransformacion;
    }

    public void mover(Personaje unPersonaje, Camino camino) throws NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException {
    	try {
			camino.siDistanciaEsMayor( efecto.aplicarEfectoVelocidad(velocidad), NoPuedeMoverAEsaDistanciaException.class );
		} catch (InstantiationException | IllegalAccessException e) {}
		
        camino.recorrerCon(unPersonaje);
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

    public void atacarA(Personaje personajeAtacante, Personaje personajeAtacado) throws NoPuedeAtacarAEsaDistanciaException {
        Ataque ataque = new Ataque(personajeAtacante, personajeAtacado, efecto.aplicarEfectoAtaque(1));
        ataque.execute();
    }

    public Transformacion transformacionOriginal() {
        return null;
    }

    public void ataqueEspecialA(Personaje personajeAtacante, Personaje personajeAtacado) throws NoPuedeAtacarMismoEquipoException, KiInsuficienteException, NoPuedeAtacarAEsaDistanciaException {
        AtaqueEspecialHandler handler = personajeAtacante.getAtaqueEspecialHandlerContra(personajeAtacado);
        handler.ataqueEspecialA(personajeAtacado);
    }

	public void aplicarEfecto(Efecto unEfecto) {
		efecto = unEfecto;
	}
}
