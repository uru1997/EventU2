package com.codigo.json.tvpro2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

/**
 * Created by narva on 24/04/2018.
 */

public class Tab1Fragment extends Fragment {
    public static final String TAG = "Tab1Fragment";

    //private Activity context;
    //private List<Eventos> eventos;
    DatabaseReference dbEventos;
    ValueEventListener eventListener;
    /*private TextView nombre;
    private TextView fecha;
    private TextView hora;
    private ImageView imagen;*/
    String nombre;
    String fecha;
    String hora;
    String tema;
    String lugar;
    String tipo;
    String duracion;
    String costo;
    String url;
    RecyclerView mRecyclerView;
    MyAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<DatosEventos>listaDatos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab1_fragment, container, false);
        /*nombre = (TextView) view.findViewById(R.id.nombre);
        fecha = (TextView)view.findViewById(R.id.fecha);
        hora = (TextView)view.findViewById(R.id.hora);
        imagen = (ImageView) view.findViewById(R.id.imagen);*/

        listaDatos = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);




        //mLayoutManager = new LinearLayoutManager(getContext());
        
        //llenarDatos();


        //url = "https://firebasestorage.googleapis.com/v0/b/tvpro2-a861f.appspot.com/o/prueba%2Flovable-naruto-wallpaper-for-desktop-2.jpg?alt=media&token=067a34c1-07fe-4c92-abe0-81bd07bf93e5";
        //Glide.with(this /*context*/).load(url).into(imagen);

        //dbEventos = FirebaseDatabase.getInstance().getReference().child("Evento");
        //dbEventos = FirebaseDatabase.getInstance().getReference().getRoot();
        //String claveEvento = dbEventos.getKey();
        //String id = dbEventos.getKey();
        dbEventos = FirebaseDatabase.getInstance().getReference().child("Evento");
        Log.d(TAG, "onCreateView: " + dbEventos.child("Evento"));
        mAdapter = new MyAdapter(listaDatos, getContext());
        mRecyclerView.setAdapter(mAdapter);

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*nombre.setText(dataSnapshot.child("Nombre").getValue().toString());
                fecha.setText(dataSnapshot.child("Fecha").getValue().toString());
                hora.setText(dataSnapshot.child("Hora").getValue().toString());*/
                /*nombre = dataSnapshot.child("Nombre").getValue().toString();
                fecha = dataSnapshot.child("Fecha").getValue().toString();
                hora = dataSnapshot.child("Hora").getValue().toString();
                url = dataSnapshot.child("url").getValue().toString();*/
                listaDatos.removeAll(listaDatos);
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    nombre = dataSnapshot1.child("Nombre").getValue().toString();
                    fecha = dataSnapshot1.child("Fecha").getValue().toString();
                    hora = dataSnapshot1.child("Hora").getValue().toString();
                    url = dataSnapshot1.child("url").getValue().toString();
                    tema = dataSnapshot1.child("Tema").getValue().toString();
                    lugar = dataSnapshot1.child("Lugar").getValue().toString();
                    tipo = dataSnapshot1.child("Tipo_evento").getValue().toString();
                    duracion = dataSnapshot1.child("Duracion").getValue().toString();
                    costo = dataSnapshot1.child("Costo").getValue().toString();
                    listaDatos.add(new DatosEventos(nombre, fecha, hora, tema, lugar, tipo, duracion, costo, url));
                    }
                    mAdapter.notifyDataSetChanged();
                    //Glide.with(getContext()).load(url).into(imagen);
                    //listaDatos.add(new DatosEventos(nombre,fecha,hora,url));
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
