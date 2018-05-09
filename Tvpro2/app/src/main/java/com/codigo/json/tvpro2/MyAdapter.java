package com.codigo.json.tvpro2;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by narva on 1/05/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolderDatos> {
    //public static final String TAG = "MYAdapter";
    ArrayList<DatosEventos> listaDatos = new ArrayList<>();
    Context context;

    public MyAdapter(ArrayList<DatosEventos> listDatos, Context context) {
        this.listaDatos = listDatos;
        this.context = context;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.objetosdelrecycler, parent, false);
        ViewHolderDatos viewHolderDatos = new ViewHolderDatos(view, context, listaDatos);
        return viewHolderDatos;
    }


    @Override
    public void onBindViewHolder(ViewHolderDatos holder, final int position) {
        //Log.d(TAG,"onBindViewHolder: called");

        holder.nombre.setText(listaDatos.get(position).getNombre());
        holder.fecha.setText(listaDatos.get(position).getFecha());
        holder.hora.setText(listaDatos.get(position).getHora());
        //holder.url = listaDatos.get(position).getUrl();
        Glide.with(holder.itemView.getContext()).load(listaDatos.get(position).getUrl()).into(holder.foto);
        //holder.foto.setImageResource(listDatos.get(position).getUrl());

    }

    @Override
    public int getItemCount() {

        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre,fecha,hora;
        String url;
        ImageView foto;
        ArrayList<DatosEventos> datosEventos = new ArrayList<DatosEventos>();
        Context context;

        public ViewHolderDatos(View itemView, Context context, ArrayList<DatosEventos> datosEventos) {
            super(itemView);
            this.datosEventos = datosEventos;
            this.context = context;
            itemView.setOnClickListener(this);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            hora = (TextView) itemView.findViewById(R.id.hora);
            foto = (ImageView) itemView.findViewById(R.id.imagen);
         }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DatosEventos datosEventos = this.datosEventos.get(position);
            Intent intent = new Intent(this.context, DetallesEvento.class);
            this.context.startActivity(intent);
            //intent.putExtra();

        }
    }

    public void setfilter(List<DatosEventos> listaDatos){
        listaDatos = new ArrayList<>();
        //listaDatos.clear();
        listaDatos.addAll(listaDatos);
        notifyDataSetChanged();
    }
}
