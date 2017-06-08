package personaje;

import transformacion.piccolo.NormalPiccolo;

public class Piccolo extends Personaje {


    public Piccolo(){
        transformacion = new NormalPiccolo();
        vida = 500;
    }



}