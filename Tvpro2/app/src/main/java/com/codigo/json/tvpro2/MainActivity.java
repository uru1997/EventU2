package com.codigo.json.tvpro2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText user, pass;
    private Button login;
    private FirebaseAuth mAuth;
    private ProgressBar pbProgreso;
    private  FirebaseAuth.AuthStateListener listenrFire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("EventU");
        toolbar.setTitleMarginStart(440);
        //toolbar.setTitleTextAppearance();
        //toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        pbProgreso = (ProgressBar) findViewById(R.id.pbProgreso);
        pbProgreso.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();
        listenrFire = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser userE = mAuth.getCurrentUser();
                if (userE == null){
                    //NO ESTA LOGEADO

                }else{
                    //ESTA LOGEADO
                    Toast.makeText(getApplicationContext(),"Correcto y logeado", Toast.LENGTH_LONG).show();
                }
            }
        };


    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(listenrFire);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(listenrFire != null){
            mAuth.removeAuthStateListener(listenrFire);
        }
    }


    public void btngo (View view){

        String usuario = user.getText().toString();
        String contraseña = pass.getText().toString();
        String persona = "";
        String contra = "";
        //metodo para iniciar sesion usando metodo signwith emailandpassword
        if(!usuario.isEmpty() && !contraseña.isEmpty()){
            pbProgreso.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //Toast.makeText(getApplicationContext(),"Correcto", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, InicioNav.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "Credenciales incorrectas", Toast.LENGTH_LONG).show();
                    }
                    pbProgreso.setVisibility(View.INVISIBLE);
                }
            });
        }else {
            Toast.makeText(this,"Faltan campos por rellenar", Toast.LENGTH_LONG).show();
        }

    }
    public void cerrar (View view){
        mAuth.signOut();
    }

    public void registro (View view){
        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {

    }
/*
//boton de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//boton de atras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
*/
}
