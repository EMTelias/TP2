package personaje;

import excepciones.direccion.NoHayDireccionPosibleException;
import estado.Estado;
import excepciones.estado.EstadoNoTieneProximoException;
import excepciones.personaje.NoPuedeCambiarDeEstadoKiInsuficienteException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import tablero.Casillero;
import excepciones.tablero.CasilleroOcupadoException;

public abstract class Personaje {

	protected Casillero casillero;
	protected Estado estado;
	protected int ki;

	public Personaje(){
		ki = 0;
	}

	public void ubicarEn(Casillero unCasillero) {
		casillero = unCasillero;
	}


	public void moverA(Casillero casilleroDestino) throws NoPuedeMoverAEsaDistanciaException, NoPuedeMoverCaminoObstruidoException, NoHayDireccionPosibleException {
		
		if (!casillero.caminoDespejadoHasta(casilleroDestino)) throw new NoPuedeMoverCaminoObstruidoException();
		
		if (casillero.distanciaHasta(casilleroDestino) > estado.getVelocidad() ) throw new NoPuedeMoverAEsaDistanciaException();
		
		casillero.quitar();
		try {
			casilleroDestino.colocar(this);
		} catch (CasilleroOcupadoException e) {
		}

	}

	public void transformar() throws NoPuedeCambiarDeEstadoKiInsuficienteException, EstadoNoTieneProximoException{
		if (ki < estado.getKiNecesarioParaTransformar()) throw new NoPuedeCambiarDeEstadoKiInsuficienteException();
			ki-=estado.getKiNecesarioParaTransformar();
			estado = estado.getProximoEstado();


	}

	public void aumentarKi(int aumento){
		ki+=aumento;
	}



}
