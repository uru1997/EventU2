package com.codigo.json.tvpro2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codigo.json.tvpro2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by narva on 24/04/2018.
 */

public class Tab2Fragment extends Fragment {
    public static final String TAG = "Tab1Fragment";
    DatabaseReference dbEventos;
    ValueEventListener eventListener;
    String nombre;
    String fecha;
    String hora;
    String url;
    RecyclerView mRecyclerView;
    MyAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<DatosEventos> listaDatos;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab2_fragment, container, false);

        listaDatos = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        dbEventos = FirebaseDatabase.getInstance().getReference().getRoot();
        mAdapter = new MyAdapter(listaDatos);
        mRecyclerView.setAdapter(mAdapter);

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaDatos.removeAll(listaDatos);
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    nombre = dataSnapshot1.child("Nombre").getValue().toString();
                    fecha = dataSnapshot1.child("Fecha").getValue().toString();
                    hora = dataSnapshot1.child("Hora").getValue().toString();
                    url = dataSnapshot1.child("url").getValue().toString();
                    listaDatos.add(new DatosEventos(nombre,fecha,hora,url));
                }
                mAdapter.notifyDataSetChanged();
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
