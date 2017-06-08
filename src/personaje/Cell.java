package personaje;

import transformacion.cell.NormalCell;

public class Cell extends Personaje {


    public Cell(){
        transformacion = new NormalCell();
        vida = 500;
    }



}