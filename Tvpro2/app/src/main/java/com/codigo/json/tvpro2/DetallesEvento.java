package com.codigo.json.tvpro2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Debug;
import android.support.annotation.NonNull;
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
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetallesEvento extends AppCompatActivity {
    ImageView imageView;
    Button button;
    TextView nombre, costo, lugar, fecha, hora, tema, tipo, duracion;
    String nom, apellido, correo, pais, ciudad, genero, edad, documento, id_ev;
    AlertDialog dialog;
    int x,y;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener listener;

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

        mAuth = FirebaseAuth.getInstance();

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

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
        builder.setMessage(null).setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                inscrip();
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
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Desea confirmar la suscripción al evento.");
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
    public void inscrip(){

        String id_ev = getIntent().getStringExtra("id_evento");
        //Log.d("hola",id_ev);
        final String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        current_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //leer la informacion del usuario
                nom = dataSnapshot.child("Nombre_Asistente").getValue().toString();
                apellido = dataSnapshot.child("Apellidos_Asistente").getValue().toString();
                correo = dataSnapshot.child("Correo").getValue().toString();
                pais = dataSnapshot.child("Pais_Asistente").getValue().toString();
                ciudad = dataSnapshot.child("Ciudad_Asistente").getValue().toString();
                edad = dataSnapshot.child("Edad_Asistente").getValue().toString();
                genero = dataSnapshot.child("Genero").getValue().toString();
                documento = dataSnapshot.child("NroDocumento_Asistente").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

            DatabaseReference inscrip_event  = FirebaseDatabase.getInstance().getReference().child("Evento").child(id_ev).child("Usuarios_inscritos").child(user_id);
            Map newPost = new HashMap();
            newPost.put("Nombre_Asistente", nom);
            newPost.put("Apellidos_Asistente", apellido);
            newPost.put("Edad_Asistente", edad);
            newPost.put("Pais_Asistente", pais);
            newPost.put("Ciudad_Asistente", ciudad);
            newPost.put("NroDocumento_Asistente", documento);
            newPost.put("Genero", genero);
            newPost.put("Correo", correo);
            inscrip_event.setValue(newPost);
            Toast.makeText(getApplicationContext(), "Usuario inscrito correctamente", Toast.LENGTH_LONG).show();

    }
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(listener != null){
            mAuth.removeAuthStateListener(listener);
        }
    }
}
