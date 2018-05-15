package com.codigo.json.tvpro2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

import com.bumptech.glide.Glide;

public class DetallesEvento extends AppCompatActivity {
    ImageView imageView;
    Button button;
    TextView nombre, costo, lugar, fecha, hora, tema, tipo, duracion;
    AlertDialog dialog;
    int x,y;

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inscripcion();
            }
        });

    }

    public void inscripcion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DetallesEvento.this);
        builder.setMessage("Seleccione el medio con el que desea pagar la inscripción.").setPositiveButton("Tarjeta de crédito", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tarjetacredito();
            }
        }).setNegativeButton("Efectivo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                efectivo();
            }
        }).setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Medio de pago");
        alertDialog.show();
    }

    public void tarjetacredito(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DetallesEvento.this);
        View mView = getLayoutInflater().inflate(R.layout.dialogtarjetacredito, null);
        final EditText mNombre = (EditText) mView.findViewById(R.id.editTextnombre);
        final EditText mApellido = (EditText) mView.findViewById(R.id.editTextapellido);
        final EditText mNrotar = (EditText) mView.findViewById(R.id.editTextNroTarjeta);
        final EditText mCod = (EditText) mView.findViewById(R.id.editTextCodSeg);
        final EditText mDoctit = (EditText) mView.findViewById(R.id.editTextDoctit);
        Button mButton = (Button) mView.findViewById(R.id.buttonCont);

        builder.setView(mView);
        final AlertDialog dialog = builder.create();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(DetallesEvento.this);
                builder.setMessage("Tu inscripcion al evento " + getIntent().getStringExtra("nombre")+" se ha realizado correctamente.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("¡Tu inscripción ha sido exitosa!");
                alertDialog.show();
            }
        });
        dialog.show();
    }
    public  void efectivo(){
        Random rnd = new Random();

        for (int i = 100; i < 1000; i++) {
            x = (int)(rnd.nextDouble() * 1000);
            y = (int)(rnd.nextDouble() * 1000);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(DetallesEvento.this);
        builder.setMessage("Díctale estos números al cajero: "+ x +" - "+y +", para completar el pago y la inscripción al evento.").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Codigo de pago");
        alertDialog.show();
    }
}
