package personaje;

import estado.cell.NormalCell;

public class Cell extends Personaje {


    public Cell(){
        estado = new NormalCell();
        vida = 500;
    }



}