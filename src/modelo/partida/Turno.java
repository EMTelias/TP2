package modelo.partida;

import modelo.personaje.equipos.Equipo;

public class Turno {
    private Equipo equipoEnMovimiento;
    private Equipo equipoEnEspera;
    private boolean ataqueYaEjecutado;
    private boolean movimientoYaEjecutado;



    public Turno(Equipo equipo1,Equipo equipo2){
        this.equipoEnMovimiento = equipo1;
        this.equipoEnEspera = equipo2;
        equipo1.setTurno(true);
        equipo2.setTurno(false);

        ataqueYaEjecutado = false;
        movimientoYaEjecutado = false;

    }

    public Equipo getEquipoActivo(){
        return this.equipoEnMovimiento;
    }

    public Equipo getEquipoEnEspera(){
        return this.equipoEnEspera;
    }

    public void pasar() {
        Equipo equipo = this.equipoEnMovimiento;
        this.equipoEnMovimiento = this.equipoEnEspera;
        this.equipoEnEspera = equipo;
        this.equipoEnMovimiento.setTurno(true);
        this.equipoEnEspera.setTurno(false);

        ataqueYaEjecutado = false;
        movimientoYaEjecutado = false;

    }

    public boolean yaAtaco(){
        return ataqueYaEjecutado;
    }

    public boolean yaMovio(){
        return movimientoYaEjecutado;
    }

    public void atacar() { ataqueYaEjecutado = true; }

    public void mover() {
        movimientoYaEjecutado = true;
    }
}
