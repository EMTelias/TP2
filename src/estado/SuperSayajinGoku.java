package estado;

public class SuperSayajinGoku implements EstadoGoku {
	
	final int VELOCIDAD = 5;
	int kiNecesarioParaEvolucionar = 0;
	
	@Override
	public int getVelocidad() {
		return VELOCIDAD;
	}

	@Override
	public Estado getProximoEstado() throws EstadoNoTieneProximoException { throw new EstadoNoTieneProximoException(); }

	@Override
	public int getKiNecesarioParaTransformar() {return kiNecesarioParaEvolucionar;}
}
