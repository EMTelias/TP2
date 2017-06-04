package personaje;

import estado.piccolo.NormalPiccolo;

public class Piccolo extends Personaje {


    public Piccolo(){
        estado = new NormalPiccolo();
    }



}