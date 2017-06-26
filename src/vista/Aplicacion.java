package vista;

import controlador.eventos.AplicacionOnKeyPressEventHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aplicacion extends Application {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {

        this.stage = stage;
        empezarJuego();
        stage.show();

    }

    public void cerrar() {
        stage.close();
    }

    public void reiniciar() throws Exception {
        empezarJuego();
        stage.show();
    }

    public void empezarJuego() throws Exception{
        stage.setTitle("Dragon AlgoBall");

        ContenedorPrincipal contenedorPrincipal = new ContenedorPrincipal(stage, this);
        Scene escenaJuego = new Scene(contenedorPrincipal, 800, 600);

        AplicacionOnKeyPressEventHandler AplicacionOnKeyPressEventHandler = new AplicacionOnKeyPressEventHandler(stage, contenedorPrincipal.getBarraDeMenu());
        escenaJuego.setOnKeyPressed(AplicacionOnKeyPressEventHandler);

        ContenedorBienvenidos contenedorBienvenidos = new ContenedorBienvenidos(stage, escenaJuego);
        Scene escenaBienvenidos = new Scene(contenedorBienvenidos, 800, 600);


        stage.setScene(escenaBienvenidos);
        stage.setFullScreen(true);

    }
}
