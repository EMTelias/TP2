package modelo.acciones;


import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.personaje.Personaje;

public class Ataque implements Command{

    private final Personaje atacante;
    private final Personaje atacado;
    private final double multiplicadorFinal;
    private double multiplicador = 1;

    public Ataque(Personaje atacante, Personaje atacado, double multiplicadorFinal) {
        this.atacante = atacante;
        this.atacado = atacado;
        this.multiplicadorFinal = multiplicadorFinal;

    }

    @Override
    public void execute() throws NoPuedeAtacarAEsaDistanciaException{
        this.verificarPoderDePelea();
        this.verificarDistancias();
        int damage = this.obtenerDanioFinal();
        this.atacado.reducirVida(damage);
    }

    private void verificarPoderDePelea() {
        if (this.atacante.getPoderDePelea() < this.atacado.getPoderDePelea()) {
            this.multiplicador -= 0.2;
        }
    }

    private void verificarDistancias() throws NoPuedeAtacarAEsaDistanciaException {
        if (this.atacante.distanciaA(this.atacado) > this.atacante.getDistanciaAtaque()) {
            throw new NoPuedeAtacarAEsaDistanciaException();
        }
    }

    private int obtenerDanioFinal() {
        return (int)(this.atacante.getPoderDePelea() * this.multiplicadorFinal * this.multiplicador);
    }

}
