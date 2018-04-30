package com.codigo.json.tvpro2;

import android.widget.Spinner;

/**
 * Created by Andres Felipe on 25/04/2018.
 */

public class Usuarios {

    String nombre;
    String apellido;
    String genero;
    String correo;

    public Usuarios(){

    }

    public Usuarios(String userId, String nombre, String apellido, String genero, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.correo = correo;
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getGenero() {
        return genero;
    }

    public String getCorreo() {
        return correo;
    }
}
