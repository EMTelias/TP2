package controlador.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import modelo.Consumibles.GeneradorDeConsumibles;
import modelo.Consumibles.GeneradorDeConsumiblesRandom;
import modelo.partida.Partida;
import modelo.tablero.Posicion;
import vista.TileConsumible;
import vista.VistaTablero;

public class BotonPasarHandler implements EventHandler<ActionEvent> {

    private final Partida partida;
    private final VistaTablero vistaTablero;

    public BotonPasarHandler(Partida partida, VistaTablero vistaTablero) {
        this.partida = partida;
        this.vistaTablero = vistaTablero;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        /*ArrayList<Casillero> casilleros = new ArrayList<>();
        casilleros.add(partida.getTablero().getCasillero(new Posicion(2,2)));
        try {
            partida.moverEnCamino(new Posicion(1,1), new Camino(casilleros));
        } catch (NoPuedeMoverAEsaDistanciaException e) {
            e.printStackTrace();
        } catch (NoPuedeMoverCaminoObstruidoException e) {
            e.printStackTrace();
        } catch (CaminoInvalidoException e) {
            e.printStackTrace();
        }*/
        partida.pasar();

        //genero posible consumible random
        GeneradorDeConsumiblesRandom generadorConsmibles = new GeneradorDeConsumiblesRandom();
        Posicion posicionGenerada = generadorConsmibles.generarConsumibleEn(partida.getTablero(),partida.getTablero().getDimensionX(),partida.getTablero().getDimensionY());

        //Si efectivamente se creo:
        //if(posicionGenerada != null){ new TileConsumible(imageDirectory + "Consumible" + IMG_EXT, 0,0,seleccionarHandler);}


        vistaTablero.actualizarVista();
    }
}