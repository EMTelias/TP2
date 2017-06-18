package partida;

import equipos.Equipo;

public class Turno {
    private Equipo equipoEnMovimiento;
    private Equipo equipoEnEspera;

    public Turno(Equipo equipo1,Equipo equipo2){
        this.equipoEnMovimiento = equipo1;
        this.equipoEnEspera = equipo2;
        equipo1.setTurno(true);
        equipo2.setTurno(false);
    }
    public Equipo getEquipoActivo(){
        return this.equipoEnMovimiento;
    }

    public void pasar() {
        Equipo equipo = this.equipoEnMovimiento;
        this.equipoEnMovimiento = this.equipoEnEspera;
        this.equipoEnEspera = equipo;
        this.equipoEnMovimiento.setTurno(true);
        this.equipoEnEspera.setTurno(false);
    }
}
