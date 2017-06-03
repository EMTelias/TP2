package personaje;

import estado.Estado;
import tablero.Casillero;
import tablero.CasilleroOcupadoException;

public abstract class Personaje {

	protected Casillero casillero;
	protected Estado estado;
	

	public void ubicarEn(Casillero unCasillero) {
		casillero = unCasillero;
	}


	public void moverA(Casillero casilleroDestino) throws NoPuedeMoverAEsaDistanciaException {
		
		if (casillero.distanciaHasta(casilleroDestino) > estado.getVelocidad() ) throw new NoPuedeMoverAEsaDistanciaException();
		
		casillero.quitar();
		try {
			casilleroDestino.colocar(this);
		} catch (CasilleroOcupadoException e) {
		}
		
	}

}
