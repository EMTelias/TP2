package modelo.personaje.transformacion;


import modelo.Consumibles.Efecto;
import modelo.Consumibles.SinEfectoEspecial;
import modelo.acciones.Ataque;
import modelo.acciones.AtaqueEspecialHandler;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import modelo.excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import modelo.excepciones.transformacion.*;
import modelo.personaje.Personaje;
import modelo.tablero.Camino;

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


    public Transformacion transformar(Personaje unPersonaje) {
        int kiActual = unPersonaje.getKi();

        if (kiActual < this.kiNecesarioTransformar) {
            throw new KiInsuficienteException();
        }

        unPersonaje.reducirKi(this.kiNecesarioTransformar);
        this.proximaTransformacion.aplicarEfecto(efecto);
        return this.proximaTransformacion;
    }

    public void mover(Personaje unPersonaje, Camino camino) {
    	try {
			camino.siDistanciaEsMayor( efecto.aplicarEfectoVelocidad(velocidad), NoPuedeMoverAEsaDistanciaException.class );
		} catch (InstantiationException | IllegalAccessException e) {}
		
        camino.recorrerCon(unPersonaje);
    }

    public int getVelocidad() {
        return efecto.aplicarEfectoVelocidad(this.velocidad);
    }

    public int getPoderDePelea() {
        return this.poderDePelea;
    }

    public int getDistanciaAtaque() {
        return this.distanciaAtaque;
    }

    public void atacarA(Personaje personajeAtacante, Personaje personajeAtacado) {
        Ataque ataque = new Ataque(personajeAtacante, personajeAtacado, efecto.aplicarEfectoAtaque(1));
        ataque.execute();
    }

    public Transformacion transformacionOriginal() {
        return null;
    }

    public void ataqueEspecialA(Personaje personajeAtacante, Personaje personajeAtacado) {
        AtaqueEspecialHandler handler = personajeAtacante.getAtaqueEspecialHandlerContra(personajeAtacado);
        handler.ataqueEspecialA(personajeAtacado);
    }

	public void aplicarEfecto(Efecto unEfecto) {
		efecto = unEfecto;
	}

    public Transformacion revisarTransformacionChocolate(){return null;}

    public boolean esChocolate(){return false;}

    public int aumentarKi(int aumento){return aumento;}

	public void pasarTurno() {
		efecto = efecto.pasarTurno();
		
	}
}
