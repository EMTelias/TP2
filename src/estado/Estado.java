package estado;


public interface Estado {

	int getVelocidad();
	Estado getProximoEstado()throws EstadoNoTieneProximoException;
	int getKiNecesarioParaTransformar();
}
