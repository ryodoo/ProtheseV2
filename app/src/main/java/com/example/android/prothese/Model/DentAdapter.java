package com.example.android.prothese.Model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by filipp on 9/16/2016.
 */
public class DentAdapter extends RecyclerView.Adapter<DentAdapter.ViewHolder> {

    private Context context;
    private List<Commande> my_data;
    public View itemView ;
    public DentAdapter(Context context, List<Commande> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dent,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Date date_commande= null;
        String codeColor="#FF3DE018";
        Date date_etat=null;
        int nbjour=-1;
        try {
            date_commande = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(my_data.get(position).commande_created);
            for(int k=0;k<my_data.get(position).etats.size();k++)
            {
                if(!my_data.get(position).etats.get(k).getDatefin().equals("null"))
                {
                    date_etat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(my_data.get(position).etats.get(k).getDatefin());
                    nbjour = (int)( ( new Date().getTime() - date_etat.getTime())/ (1000 * 60 * 60 * 24) );
                    if(nbjour>Integer.parseInt(my_data.get(position).etats.get(k).getJour()))
                        codeColor="#FFE03618";
                    else if (nbjour==Integer.parseInt(my_data.get(position).etats.get(k).getJour()))
                        codeColor="#FFFFEB3B";
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.imagecolor.setBackgroundColor(Color.parseColor(codeColor));
        int diffInDays = (int)( ( new Date().getTime() - date_commande.getTime())/ (1000 * 60 * 60 * 24) );
        holder.commande.setText(my_data.get(position).client_nom);
        holder.jours.setText("N° jours : "+diffInDays+" J");
        holder.patient_nom.setText(my_data.get(position).patient_nom);
        holder.patient_age.setText(my_data.get(position).patient_age+" ans");
        if(my_data.get(position).patient_sexe.equals("h"))
            holder.patient_sexe.setText("Homme");
        else
            holder.patient_sexe.setText("Femme");
        if(my_data.get(position).patient_visage.equals("1"))
            holder.patient_visage.setImageResource(R.drawable.carre);
        else if(my_data.get(position).patient_visage.equals("2"))
            holder.patient_visage.setImageResource(R.drawable.triengle);
        else
            holder.patient_visage.setImageResource(R.drawable.cercle);
        holder.etat.setText(my_data.get(position).commande_etat);
        holder.dents.setText("N° jour : "+nbjour+" J");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailDent.class);
                intent.putExtra("position", position);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public ImageView imagecolor;
        public TextView commande;
        public TextView jours;
        public TextView etat;
        public TextView patient_nom;
        public TextView patient_age;
        public TextView patient_sexe;
        public ImageView patient_visage;
        public TextView dents;
        public View lyt_parent;

        public ViewHolder(View itemView) {
            super(itemView);
            imagecolor =  itemView.findViewById(R.id.imagecolor);
            commande =  itemView.findViewById(R.id.commande);
            jours =  itemView.findViewById(R.id.jours);
            etat =  itemView.findViewById(R.id.etat);
            patient_nom =  itemView.findViewById(R.id.patient_nom);
            patient_age =  itemView.findViewById(R.id.patient_age);
            patient_sexe =  itemView.findViewById(R.id.patient_sexe);
            patient_visage =  itemView.findViewById(R.id.visage);
            dents =  itemView.findViewById(R.id.dents);
            lyt_parent = itemView.findViewById(R.id.lyt_parent);
        }
    }



}
