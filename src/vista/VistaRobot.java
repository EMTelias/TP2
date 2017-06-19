package vista;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import modelo.partida.Partida;

public class VistaRobot {

    private Partida partida;
    Canvas canvas;

    public VistaRobot(Partida partida, Canvas canvas) {
        this.partida = partida;
        this.canvas = canvas;
    }

    public void dibujar() {
        this.dibujarFormas();
    }

    private void dibujarFormas() {
        //this.clean();
        //canvas.getGraphicsContext2D().setFill(Color.DARKBLUE);
        //canvas.getGraphicsContext2D().fillOval(robot.getPosicion().getX() + 230, robot.getPosicion().getY() + 110, robot.RADIO, robot.RADIO);
    }

    public void clean() {

        canvas.getGraphicsContext2D().setFill(Color.LIGHTBLUE);
        canvas.getGraphicsContext2D().fillRect(0, 0, 460, 220);
    }

    public void update() {
        this.dibujar();
    }

}
