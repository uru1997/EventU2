package com.codigo.json.tvpro2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.data.Freezable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Perfil extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView nomb, corr;
    private List<Usuarios> infor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        infor = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();

        nomb = (TextView) findViewById(R.id.nombre);
        corr = (TextView) findViewById(R.id.correo);

        //Aregar boton de regreso
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Intent intent = new Intent(Perfil.this, InicioNav.class);
                startActivity(intent);
                finish();
            }
        });
        //------------------Boton de regreso--------

        //obtener la informacion de solo el usuario registrado
        final String user_id = mAuth.getCurrentUser().getUid();
        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        current_user_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //leer la informacion del usuario
                String nombre = dataSnapshot.child("Nombre_Asistente").getValue().toString();
                String apellido = dataSnapshot.child("Apellidos_Asistente").getValue().toString();
                String correo = dataSnapshot.child("Correo").getValue().toString();
                String genero = dataSnapshot.child("Genero").getValue().toString();
                nomb.setText(nombre+" "+apellido);
                corr.setText(correo);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
