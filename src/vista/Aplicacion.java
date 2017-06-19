package vista;

import controlador.SeleccionarHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        SeleccionarHandler seleccionarHandler = new SeleccionarHandler(partida);

        ContenedorPrincipal contenedorPrincipal = new ContenedorPrincipal(stage, partida, seleccionarHandler);
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
        Partida partida = new Partida();
        return partida;
    }
}
