package com.codigo.json.tvpro2;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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
        String correo = Correo.getText().toString().trim();
        String contra = Contra.getText().toString().trim();

        if(!TextUtils.isEmpty(correo)&&!TextUtils.isEmpty(contra)){
            mAuth.createUserWithEmailAndPassword(correo,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        //verifica si ya hay un usuario con el correo registrado
                        if (task.getException() instanceof FirebaseAuthActionCodeException) {
                            Toast.makeText(getApplicationContext(), "Usuario ya registrado", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }else {

                        String user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                        String nombre = Nombre.getText().toString().trim();
                        String apellido = Apellido.getText().toString().trim();
                        String correo = Correo.getText().toString().trim();
                        String genre = (String) Genre.getSelectedItem();

                        Map newPost = new HashMap();
                        newPost.put("Nombre", nombre);
                        newPost.put("Apellido", apellido);
                        newPost.put("Genero", genre);
                        newPost.put("Correo", correo);
                        current_user_db.setValue(newPost);
                        Toast.makeText(getApplicationContext(), "Usuario añadido correctamente", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Registrar.this, MainActivity.class);
                        startActivity(intent);
                        finish();
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
