package controlador.eventos;

import controlador.CaminoController;
import controlador.PersonajeController;
import controlador.SeleccionarHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import modelo.excepciones.JuegoTerminadoException;
import modelo.excepciones.acciones.NoPuedeAtacarMismoEquipoException;
import modelo.excepciones.acciones.NoPuedeMoverseSiendoChocolateException;
import modelo.excepciones.acciones.NoPuedeUsarAtaqueEspecialSiendoChocolateException;
import modelo.excepciones.acciones.YaAtacasteEsteTurnoException;
import modelo.excepciones.personaje.NoEsSuTurnoException;
import modelo.excepciones.personaje.NoPuedeAtacarAEsaDistanciaException;
import modelo.excepciones.transformacion.KiInsuficienteException;
import modelo.partida.Partida;
import modelo.personaje.Personaje;
import modelo.personaje.equipos.Equipo;
import modelo.tablero.Posicion;
import vista.Aplicacion;
import vista.VistaInfo;
import vista.VistaTablero;

public class BotonAtaqueEspecialHandler implements EventHandler<ActionEvent> {

    private final Partida partida;
    private final VistaTablero vistaTablero;
    private final PersonajeController personajeController;
    private final CaminoController caminoController;
    private final VistaInfo info;
    private final Aplicacion app;
    private Label consola;
    
    public BotonAtaqueEspecialHandler(Aplicacion app, VistaTablero vistaTablero, Partida partida, CaminoController caminoController, PersonajeController personajeController, VistaInfo info, Label unaConsola) {
        this.app = app;
        this.partida = partida;
        this.vistaTablero = vistaTablero;
        this.caminoController = caminoController;
        this.personajeController = personajeController;
        this.info = info;
        this.consola = unaConsola;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
    	Equipo equipoJugando = partida.turnoActual();
        Personaje atacante = personajeController.obtenerPersonaje();
        Personaje atacado = personajeController.obtenerPersonaje();
        try {
            partida.ataqueEspecial(atacante, atacado);
        } catch (NoPuedeAtacarMismoEquipoException e) {
            consola.setText(atacante.getClass().getSimpleName() + " no puede atacar a su amigo " + atacado.getClass().getSimpleName() + "!");
        } catch (NoPuedeAtacarAEsaDistanciaException e) {
            consola.setText(atacante.getClass().getSimpleName() + " no puede atacar a esa distancia.");
        } catch (NoPuedeUsarAtaqueEspecialSiendoChocolateException e) {
            consola.setText(atacante.getClass().getSimpleName() + " no puede usar ataque especial este turno. Es chocolate.");
        } catch (NoEsSuTurnoException e){
            consola.setText("No es el turno de este personaje.");
        } catch (KiInsuficienteException e) {
            consola.setText(atacante.getClass().getSimpleName() + " no posee ki suficiente.");
        } catch (YaAtacasteEsteTurnoException e) {
            consola.setText("El equipo de " + atacante.getClass().getSimpleName() + " ya ataco este turno!");
        } catch (JuegoTerminadoException e) {
            consola.setText("Ha ganado el equipo de los " + equipoJugando.getNombre() + ". FELICITACIONES!");
            new VentanaJuegoTerminado(app);
        }

        personajeController.limpiar();
        caminoController.limpiar();
        if (equipoJugando.getNombre().equals(partida.turnoActual().getNombre())) {
            info.setAtaques("0");
        } else {
            consola.setText("Comienza el turno de los " + partida.turnoActual().getNombre());
            info.setTurno(partida.turnoActual().getNombre());
            info.setAccionesDefault();
        }
        info.setStatsDefault();
        vistaTablero.actualizarVista();
    }
}
