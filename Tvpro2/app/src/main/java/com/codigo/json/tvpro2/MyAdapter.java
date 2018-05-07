package com.codigo.json.tvpro2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by narva on 1/05/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolderDatos> {
    ArrayList<DatosEventos> listaDatos;
    Context context;

    public MyAdapter(ArrayList<DatosEventos> listDatos) {
        this.listaDatos = listDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.objetosdelrecycler, parent, false);
        ViewHolderDatos viewHolderDatos = new ViewHolderDatos(view);
        return viewHolderDatos;
    }


    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {

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

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView nombre,fecha,hora;
        String url;
        ImageView foto;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            hora = (TextView) itemView.findViewById(R.id.hora);
            foto = (ImageView) itemView.findViewById(R.id.imagen);
        }
    }
}
