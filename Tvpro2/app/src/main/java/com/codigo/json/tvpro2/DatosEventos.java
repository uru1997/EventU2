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
    private String tema;
    private String lugar;
    private String tipo;
    private String duracion;
    private String costo;
    private String url;
    private String id_evento;

    public DatosEventos() {

    }

    public DatosEventos(String nombre, String fecha, String hora, String tema, String lugar, String tipo, String duracion, String costo, String url, String id_evento) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.tema = tema;
        this.lugar = lugar;
        this.tipo = tipo;
        this.duracion = duracion;
        this.costo = costo;
        this.url = url;
        this.id_evento = id_evento;
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

    public String getTema() {
        return tema;
    }

    public String getLugar() {
        return lugar;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getCosto() {
        return costo;
    }

    public String getUrl() {
        return url;
    }

    public String getId_evento() {   return id_evento;    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId_evento(String id_evento) {this.id_evento = id_evento; }
}
