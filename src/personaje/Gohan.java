package personaje;

import estado.gohan.NormalGohan;

public class Gohan extends Personaje {

    public Gohan(){
        estado = new NormalGohan();
        vida = 300;
    }

}
