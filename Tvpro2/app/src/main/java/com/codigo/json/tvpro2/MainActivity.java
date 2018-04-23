package com.codigo.json.tvpro2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText user, pass;
    private Button login;


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




    }
    public void btngo (View view){

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);

        String usuario = user.getText().toString();
        String contraseña = pass.getText().toString();
        String persona = "";
        String contra = "";

        if (usuario.equals(persona) && contraseña.equals(contra)){
            Intent intent = new Intent(this,InicioNav.class);
            startActivity(intent);
        }
        else{
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

            dialogo.setTitle("¡No tienes una cuenta en EventU!");
            dialogo.setMessage("Registrate ahora");
            dialogo.setPositiveButton("Volver", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.cancel();
                }
            });

            dialogo.create();
            dialogo.show();
        }
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
