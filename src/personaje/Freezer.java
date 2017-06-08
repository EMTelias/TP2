package personaje;

import transformacion.freezer.NormalFreezer;

public class Freezer extends Personaje {


    public Freezer(){
        transformacion = new NormalFreezer();
        vida = 400;
    }



}