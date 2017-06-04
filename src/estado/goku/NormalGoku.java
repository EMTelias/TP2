package estado.goku;

import estado.Estado;

public class NormalGoku implements EstadoGoku {
	
	final int VELOCIDAD = 2;
	Estado proximoEstado = new KaioKenGoku();
	final int kiNecesarioParaEvolucionar = 20;
	
	@Override
	public int getVelocidad() {
		return VELOCIDAD;
	}

	@Override
	public Estado getProximoEstado() { return proximoEstado; }

	@Override
	public int getKiNecesarioParaTransformar() {return kiNecesarioParaEvolucionar;}
}
