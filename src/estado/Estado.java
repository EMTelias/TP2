package estado;


import excepciones.estado.EstadoNoTieneProximoException;

public interface Estado {

	int getVelocidad();
	Estado getProximoEstado()throws EstadoNoTieneProximoException;
	int getKiNecesarioParaTransformar();
	int getPoderDePelea();
}
