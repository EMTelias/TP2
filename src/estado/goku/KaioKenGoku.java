package estado.goku;

import estado.Estado;

public class KaioKenGoku implements EstadoGoku {
	
	final int VELOCIDAD = 3;
	Estado proximoEstado = new SuperSayajinGoku();
	final int kiNecesarioParaEvolucionar = 50;
	
	@Override
	public int getVelocidad() {
		return VELOCIDAD;
	}

	@Override
	public Estado getProximoEstado() { return proximoEstado; }

	@Override
	public int getKiNecesarioParaTransformar() {return kiNecesarioParaEvolucionar;}

}
