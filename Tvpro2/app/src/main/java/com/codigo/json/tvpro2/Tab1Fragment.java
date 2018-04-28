package com.codigo.json.tvpro2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.codigo.json.tvpro2.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by narva on 24/04/2018.
 */

public class Tab1Fragment extends Fragment {
    public static final String TAG = "Tab1Fragment";

    //private Activity context;
    //private List<Eventos> eventos;
    DatabaseReference dbEventos;
    ValueEventListener eventListener;
    private TextView nombre;
    private TextView fecha;
    private TextView hora;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab1_fragment, container, false);
        nombre = (TextView) view.findViewById(R.id.nombre);
        //fecha = (TextView)view.findViewById(R.id.fecha);
        //hora = (TextView)view.findViewById(R.id.hora);

        dbEventos = FirebaseDatabase.getInstance().getReference().child("Evento");
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nombre.setText(dataSnapshot.child("Nombre").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbEventos.addValueEventListener(eventListener);



        return view;
    }
    public void onActivity(){

    }
}
