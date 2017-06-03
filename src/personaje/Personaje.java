package personaje;

import direccion.NoHayDireccionPosibleException;
import estado.Estado;
import tablero.Casillero;
import tablero.CasilleroOcupadoException;

public abstract class Personaje {

	protected Casillero casillero;
	protected Estado estado;
	

	public void ubicarEn(Casillero unCasillero) {
		casillero = unCasillero;
	}


	public void moverA(Casillero casilleroDestino) throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoHayDireccionPosibleException {
		
		if (casillero.distanciaHasta(casilleroDestino) > estado.getVelocidad() ) throw new NoPuedeMoverAEsaDistanciaException();
		
		if (!casillero.caminoDespejadoHasta(casilleroDestino)) throw new NoPuedeMoverCaminoObstruidoException();
		
		casillero.quitar();
		try {
			casilleroDestino.colocar(this);
		} catch (CasilleroOcupadoException e) {
		}
		
	}

}
