package vista;

import controlador.CaminoController;
import controlador.PersonajeController;
import controlador.SeleccionarHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Consumibles.GeneradorDeConsumiblesRandom;
import modelo.Consumibles.GeneradorDeConsumiblesSinActividad;
import modelo.excepciones.tablero.CasilleroOcupadoException;
import modelo.excepciones.tablero.DimensionDeTableroInvalidoException;
import modelo.partida.Partida;
import controlador.eventos.AplicacionOnKeyPressEventHandler;

public class Aplicacion extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {

        stage.setTitle("Dragon AlgoBall");

        Partida partida = crearModelo();
        //SeleccionarHandler seleccionarHandler = new SeleccionarHandler(partida);
        CaminoController caminoController = new CaminoController();
        PersonajeController personajeController = new PersonajeController();

        ContenedorPrincipal contenedorPrincipal = new ContenedorPrincipal(stage, partida, caminoController, personajeController);
        Scene escenaJuego = new Scene(contenedorPrincipal, 800, 600);

        AplicacionOnKeyPressEventHandler AplicacionOnKeyPressEventHandler = new AplicacionOnKeyPressEventHandler(stage, contenedorPrincipal.getBarraDeMenu());
        escenaJuego.setOnKeyPressed(AplicacionOnKeyPressEventHandler);

        ContenedorBienvenidos contenedorBienvenidos = new ContenedorBienvenidos(stage, escenaJuego);
        Scene escenaBienvenidos = new Scene(contenedorBienvenidos, 800, 600);

        // add handler to this:
        // stage.setOnCloseRequest()

        stage.setScene(escenaBienvenidos);
        stage.setFullScreen(true);

        stage.show();

    }

    private Partida crearModelo() throws CasilleroOcupadoException, DimensionDeTableroInvalidoException {
        //GeneradorDeConsumiblesRandom generador = new GeneradorDeConsumiblesRandom();
        Partida partida = new Partida();
        //partida.setGeneradorDeConsumibles(generador);
        return partida;
    }
}
