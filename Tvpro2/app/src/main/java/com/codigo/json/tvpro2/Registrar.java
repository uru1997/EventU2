package com.codigo.json.tvpro2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrar extends AppCompatActivity {

    private EditText Nombre, Apellido, Correo, Contra;
    private Button Registro;
    private Spinner Genre;
    private DatabaseReference databaseusuarios;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseusuarios = FirebaseDatabase.getInstance().getReference("usuarios");

        mAuth = FirebaseAuth.getInstance();

        Nombre = (EditText) findViewById(R.id.nombre);
        Apellido = (EditText) findViewById(R.id.apellido);
        Correo = (EditText) findViewById(R.id.correo);
        Contra = (EditText) findViewById(R.id.contra);

        Registro = (Button) findViewById(R.id.Buttonregistro);
        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registro();
            }
        });

        Genre = (Spinner) findViewById(R.id.spinnerG);
        String []opciones = {"Masculino", "Femenino"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        Genre.setAdapter(adapter);

        listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

    }

    private void registro (){
        String nombre = Nombre.getText().toString().trim();
        String apellido = Apellido.getText().toString().trim();
        String genre = (String) Genre.getSelectedItem();
        String correo = Correo.getText().toString().trim();
        String contra = Contra.getText().toString().trim();
        if(!TextUtils.isEmpty(nombre)&&!TextUtils.isEmpty(apellido)&&!TextUtils.isEmpty(genre)&&!TextUtils.isEmpty(correo)&&
                !TextUtils.isEmpty(contra)){

            String id = databaseusuarios.push().getKey();
            Usuarios usuario = new Usuarios(id, nombre,apellido,genre,correo);
            databaseusuarios.child(id).setValue(usuario);

            mAuth.createUserWithEmailAndPassword(correo,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Error al registrar", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Usuario añadido correctamente", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else {
            Toast.makeText(this,"Faltan campos por rellenar", Toast.LENGTH_LONG).show();
        }

    }
    @Override
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

    public void onClick(View view) {

    }

}
