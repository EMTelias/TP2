package modelo.tablero;

public class Posicion {

	private int posicionX;
	private int posicionY;
	
	public Posicion(int valX, int valY){
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

	 public int hashCode(){
		 //para que funcione como clave en el hashMap
			return ((posicionX*100)+posicionY);
		}
	

	public int distanciaHasta(Posicion posicion) {
		int distanciaEnX = Math.abs(posicionX - posicion.posicionX);
		int distanciaEnY = Math.abs(posicionY - posicion.posicionY);
		return (Math.max(distanciaEnX, distanciaEnY));
	}


	public int getPosicionX() {
		return posicionX;
	}
	
	public int getPosicionY() {
		return posicionY;
	}

	public Posicion restar(Posicion otraPosicion) {
		return new Posicion(this.posicionX-otraPosicion.posicionX, this.posicionY -otraPosicion.posicionY);
	}

    public boolean esIgual(Posicion posicion) {
	 	if (this.posicionX == posicion.posicionX && this.posicionY == posicion.posicionY){
	 	    return true;
        }else{
	 	    return false;
        }
    }
}


