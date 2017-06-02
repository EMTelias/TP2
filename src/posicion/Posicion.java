package posicion;

public class Posicion {

	private int X;
	private int Y;
	
	public Posicion(int valX, int valY) throws PosicionInvalidaException{
		if ( (valX<0) || (valY<0) ) throw new PosicionInvalidaException();
		
		X = valX;
		Y = valY;
	}
	
}
