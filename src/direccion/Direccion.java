package direccion;

import tablero.Posicion;

public abstract class Direccion {

	private int direccionX;
	private int direccionY;
	
	public Direccion(int valorX,int valorY){
		direccionX = valorX;
		direccionY = valorY;
	}

	public static Direccion getDireccion(Posicion posicionInicial, Posicion posicionFinal) throws NoHayDireccionPosibleException{
		
		if (posicionInicial.equals(posicionFinal)) throw new NoHayDireccionPosibleException();
		
		Posicion posicion = posicionFinal.restar(posicionInicial);
		
		int posicionX = posicion.getPosicionX();
		int posicionY = posicion.getPosicionY();
		
		if ( posicionX == 0 ){
			if ( posicionY < 0 ) return new Sur();
			else return new Norte();
		}
		
		if ( posicionY == 0){
			if (posicionX < 0) return new Oeste();
			else return new Este();	
		}
		
		if (posicionX == posicionY){
			if ( posicionX > 0) return new Noreste();
			else return new Suroeste();
		}
		
		if ( Math.abs(posicionX) == Math.abs(posicionY)){
			if ( posicionX > 0) return new Sureste();
			else return new Noroeste();
		}
		
		throw new NoHayDireccionPosibleException();
	}
	
	public int getDireccionX(){
		return direccionX;
	}

	public int getDireccionY(){
		return direccionY;
	}
	
}
