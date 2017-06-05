package personaje;

import estado.freezer.NormalFreezer;

public class Freezer extends Personaje {


    public Freezer(){
        estado = new NormalFreezer();
        vida = 400;
    }



}