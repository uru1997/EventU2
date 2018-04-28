package com.codigo.json.tvpro2;

/**
 * Created by narva on 25/04/2018.
 */

public class Eventos {
    String nombre;
    String fecha;
    String hora;

    public Eventos(){

    }

    public Eventos(String nombre, String fecha, String hora) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
