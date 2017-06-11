package estado;


import excepciones.estado.EstadoNoTieneProximoException;
import excepciones.personaje.NoPuedeMoverAEsaDistanciaException;
import excepciones.personaje.NoPuedeMoverCaminoObstruidoException;
import excepciones.tablero.NoHayQuienRecorraException;
import tablero.Camino;

public interface Estado {

	void mover(Camino camino) throws NoPuedeMoverCaminoObstruidoException, NoPuedeMoverAEsaDistanciaException, NoHayQuienRecorraException;

	Estado getProximoEstado()throws EstadoNoTieneProximoException;
	int getKiNecesarioParaTransformar();
	int getPoderDePelea();
	int getDistanciaDeAtaque();
	
}
