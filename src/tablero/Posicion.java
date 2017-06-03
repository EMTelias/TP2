package tablero;

public class Posicion {

	private int posicionX;
	private int posicionY;
	
	public Posicion(int valX, int valY) throws PosicionInvalidaException{
		if ( (valX<0) || (valY<0) ) throw new PosicionInvalidaException();
		
		posicionX = valX;
		posicionY = valY;
	}
	
	
	 public boolean equals (Object obj){
		 Posicion otraPosicion = (Posicion) obj;
		 if ( (this.posicionX == otraPosicion.posicionX) && (this.posicionY == otraPosicion.posicionY ) )
			 return true;
		 else
			 return false;
	 } 
	
}
