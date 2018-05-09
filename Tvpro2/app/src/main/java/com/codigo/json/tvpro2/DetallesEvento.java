package com.codigo.json.tvpro2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetallesEvento extends AppCompatActivity {
    ImageView imageView;
    Button button;
    TextView nombre, costo, lugar, fecha, hora, tema, tipo, duracion;

    //private static final String TAG = "DetallesEvento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_evento);
        //Log.d(TAG, "onCreate: started.");

        imageView = (ImageView)findViewById(R.id.imageView2);
        button = (Button) findViewById(R.id.button2);
        nombre = (TextView)findViewById(R.id.nombre);
        costo = (TextView)findViewById(R.id.costo);
        lugar = (TextView)findViewById(R.id.lugar);
        fecha = (TextView)findViewById(R.id.fecha);
        hora = (TextView)findViewById(R.id.hora);
        tema = (TextView)findViewById(R.id.tema);
        tipo = (TextView)findViewById(R.id.tipo);
        duracion = (TextView)findViewById(R.id.duracion);

        Glide.with(this).load(getIntent().getStringExtra("url")).into(imageView);
        nombre.setText(getIntent().getStringExtra("nombre"));
        costo.setText("Costo: " + getIntent().getStringExtra("costo"));
        lugar.setText("Lugar: " + getIntent().getStringExtra("lugar"));
        fecha.setText("Fecha: " + getIntent().getStringExtra("fecha"));
        hora.setText("Hora: " + getIntent().getStringExtra("hora"));
        tema.setText("Tema: " + getIntent().getStringExtra("tema"));
        tipo.setText("Tipo: " + getIntent().getStringExtra("tipo"));
        duracion.setText("Duración: " + getIntent().getStringExtra("duracion"));


    }
}
