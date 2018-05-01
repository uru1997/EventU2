package com.codigo.json.tvpro2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

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
    private ImageView imagen;
    //ImagenUrl url;
    private String url;
    //private DatabaseReference url;
    //StorageReference storageRef;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab1_fragment, container, false);
        nombre = (TextView) view.findViewById(R.id.nombre);
        fecha = (TextView)view.findViewById(R.id.fecha);
        hora = (TextView)view.findViewById(R.id.hora);
        imagen = (ImageView) view.findViewById(R.id.imagen);

        //storageRef = FirebaseStorage.getInstance().getReference().child("prueba/lovable-naruto-wallpaper-for-desktop-2.jpg");
        //url = "https://firebasestorage.googleapis.com/v0/b/tvpro2-a861f.appspot.com/o/prueba%2Flovable-naruto-wallpaper-for-desktop-2.jpg?alt=media&token=067a34c1-07fe-4c92-abe0-81bd07bf93e5";
        //url = FirebaseDatabase.getInstance().getReference().child("Evento/url");
        //DatabaseReference imgUrl = FirebaseDatabase.getInstance().getReference().child("Evento").child("");
        //Glide.with(this /*context*/).load(url).into(imagen);

        dbEventos = FirebaseDatabase.getInstance().getReference().child("Evento");
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nombre.setText(dataSnapshot.child("Nombre").getValue().toString());
                fecha.setText(dataSnapshot.child("Fecha").getValue().toString());
                hora.setText(dataSnapshot.child("Hora").getValue().toString());
                url = dataSnapshot.child("url").getValue().toString();
                Glide.with(getContext()).load(url).into(imagen);
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
