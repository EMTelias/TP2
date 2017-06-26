package controlador.eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import vista.Aplicacion;

import java.util.Optional;


public class VentanaJuegoTerminado {


    public VentanaJuegoTerminado(Aplicacion app) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dragon AlgoBall");
        alert.setHeaderText("Juego terminado!");
        alert.setContentText("Felicitaciones al equipo ganador.");

        ButtonType reiniciar = new ButtonType("Reiniciar");
        ButtonType salir = new ButtonType("Salir");


        alert.getButtonTypes().setAll(reiniciar, salir);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == salir){
            app.cerrar();
        } else if (result.get() == reiniciar) {
            //app.reiniciar();
        }
    }
}
