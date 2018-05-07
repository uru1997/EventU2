package com.codigo.json.tvpro2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by narva on 1/05/2018.
 */

public class DatosEventos {
    private String nombre;
    private String fecha;
    private String hora;
    private String url;

    public DatosEventos() {

    }

    public DatosEventos(String nombre, String fecha, String hora, String url) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.url = url;
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

    public String getUrl() {
        return url;
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

    public void setUrl(String url) {
        this.url = url;
    }
}
