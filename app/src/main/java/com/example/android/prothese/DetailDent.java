package com.example.android.prothese;

import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.prothese.Model.Commande;
import com.example.android.prothese.Model.DentAdapter;
import com.example.android.prothese.Model.EtatAdapter;
import com.example.android.prothese.conf.Variable_general;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailDent extends AppCompatActivity {

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dent);

        Intent intent= getIntent();
        Bundle b = intent.getExtras();
        int  position = (int) b.get("position");
        Commande commande=Variable_general.commandes.get(position);

        //Info Patient
        TextView patient_nom= findViewById(R.id.patient_nom);
        patient_nom.setText(commande.patient_nom);
        TextView patient_sexe= findViewById(R.id.patient_sexe);
        if(commande.patient_sexe.equals("h"))
            patient_sexe.setText("Homme");
        else
            patient_sexe.setText("Femme");
        TextView patient_age= findViewById(R.id.patient_age);
        patient_age.setText(commande.patient_age +" Ans");
        ImageView patient_visage= findViewById(R.id.patient_visage);
        if(commande.patient_visage.equals("1"))
            patient_visage.setImageResource(R.drawable.carre);
        else if(commande.patient_visage.equals("2"))
            patient_visage.setImageResource(R.drawable.triengle);
        else
            patient_visage.setImageResource(R.drawable.cercle);

        //Info Commande
        Button dents =findViewById(R.id.dents);
        dents.setText("Dent : "+commande.dents.size());
        TextView client_nom= findViewById(R.id.client_nom);
        client_nom.setText(commande.client_nom);
        TextView commande_created= findViewById(R.id.commande_created);
        String[] created=commande.commande_created.split(" ");
        commande_created.setText(created[0]);
        int nbjour=0;
        TextView commande_jours= findViewById(R.id.commande_jours);
        try {
            Date date_commande = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(commande.commande_created);
            nbjour = (int)( ( new Date().getTime() - date_commande.getTime())/ (1000 * 60 * 60 * 24) );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        commande_jours.setText("("+nbjour+" J)");
        TextView commande_etat= findViewById(R.id.commande_etat);
        commande_etat.setText(commande.commande_etat);

        //Les etats
        RecyclerView recyclerView = findViewById(R.id.recycler_etats);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        EtatAdapter adapter = new EtatAdapter(this, commande.etats);
        recyclerView.setAdapter(adapter);
        

        // init toolbar
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_friend_details, menu);
        return true;
    }

}
