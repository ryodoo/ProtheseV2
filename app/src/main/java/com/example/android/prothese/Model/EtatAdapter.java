package com.example.android.prothese.Model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.prothese.DetailDent;
import com.example.android.prothese.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EtatAdapter extends RecyclerView.Adapter<EtatAdapter.ViewHolder> {

    private Context context;
    private List<Etat> my_data;
    public View itemView ;
    public EtatAdapter(Context context, List<Etat> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_etat,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String codeColor="#FF3DE018";
        int nbjour=-1;
        try {
            Date date_debut = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(my_data.get(position).getDatedebut());
            Date date_fin;
            if(my_data.get(position).getDatefin().length()<6)
                date_fin=new Date();
            else
                date_fin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(my_data.get(position).getDatefin());
            nbjour = (int)( ( date_fin.getTime() - date_debut.getTime())/ (1000 * 60 * 60 * 24) );
            if(nbjour>Integer.parseInt(my_data.get(position).getJour()))
                codeColor="#FFE03618";
            else if (nbjour==Integer.parseInt(my_data.get(position).getJour()))
                codeColor="#FFFFEB3B";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("koko",my_data.get(position).toString());
        holder.imagecolor.setBackgroundColor(Color.parseColor(codeColor));
        holder.jours.setText("N° jours : "+nbjour+" J");

        holder.user.setText(my_data.get(position).getUser()+"");
        holder.date_debut.setText(my_data.get(position).getDatedebut());
        if(my_data.get(position).getDatefin().length()<6)
            holder.date_fin.setText("--");
        else
            holder.date_fin.setText(my_data.get(position).getDatefin());
        holder.info.setText(my_data.get(position).getInfo());
        holder.etat.setText(my_data.get(position).getEtat());
        holder.etat.setText(my_data.get(position).getEtat());

    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public ImageView imagecolor;
        public TextView user;
        public TextView jours;
        public TextView etat;
        public TextView date_debut;
        public TextView date_fin;
        public TextView info;

        public ViewHolder(View itemView) {
            super(itemView);
            imagecolor =  itemView.findViewById(R.id.imagecolor);
            jours =  itemView.findViewById(R.id.jours);
            user =  itemView.findViewById(R.id.user);
            date_debut =  itemView.findViewById(R.id.date_debut);
            date_fin =  itemView.findViewById(R.id.date_fin);
            info =  itemView.findViewById(R.id.info);
            etat =  itemView.findViewById(R.id.etat);
        }
    }



}
