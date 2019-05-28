package com.example.android.prothese;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.prothese.Model.Commande;
import com.example.android.prothese.Model.Dent;
import com.example.android.prothese.Model.Etat;
import com.example.android.prothese.Model.Typeetat;
import com.example.android.prothese.Model.User;
import com.example.android.prothese.conf.Variable_general;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class splashScreen extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private TelephonyManager mTelephonyManager;
    String iemi;
    int intrnet=0;
    int v=0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                        PERMISSIONS_REQUEST_READ_PHONE_STATE);
            } else {
                mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                iemi = mTelephonyManager.getDeviceId();
                SharedPreferences s=getApplicationContext().getSharedPreferences("iemii", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=s.edit();
                editor.putString("iemi",iemi);
                editor.commit();
            }
        }
        else
        {
            mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            iemi = mTelephonyManager.getDeviceId();
            SharedPreferences s=getApplicationContext().getSharedPreferences("iemii", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=s.edit();
            editor.putString("iemi",iemi);
            editor.commit();
        }


        ConnectivityManager connectivityManager = (ConnectivityManager) splashScreen.this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            intrnet=1;
        }
        else {
            AlertDialog alertDialog = new AlertDialog.Builder(splashScreen.this).create();
            alertDialog.setTitle("Erreur de connexion ");
            alertDialog.setMessage("Connection au serveur est impossible. Veuillez vérifier votre connexion internet et réessayer.");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(splashScreen.this, splashScreen.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            splashScreen.this.startActivity(intent);
                        }
                    }, 2000);
                }
            });
            alertDialog.show();
        }



        RequestQueue queue = Volley.newRequestQueue(this);
        String url=Variable_general.lien_get_user+"/"+iemi;
        Variable_general.iemi=iemi;
        Log.i("koko",iemi);
        StringRequest user = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("koko response ",response);
                        if(response.equals("1")) {
                            Variable_general.user = response;
                            Log.i("koko","im here");
                            v++;
                            aller(v);
                        }
                        else {
                            Log.i("koko","Mchit l login");
                            Intent intent = new Intent(splashScreen.this, Login.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            splashScreen.this.startActivity(intent);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        user.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                4, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(user);

        url=Variable_general.getLien_get_typeetats+"/"+iemi;
        StringRequest types = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Variable_general.json_typeetats = new JSONArray(response);
                            Variable_general.typeetats.clear();
                            for (int i = 0; i < Variable_general.json_typeetats.length(); i++) {
                                JSONObject patient = Variable_general.json_typeetats.getJSONObject(i).getJSONObject("Typeetat");
                                String id = patient.getString("id");
                                String name = patient.getString("name");
                                Typeetat type=new Typeetat(id,name);
                                Variable_general.typeetats.add(type);
                                Log.d("koko",type.toString());
                            }
                        }
                        catch (JSONException e) {
                            Log.d("koko","Erreur dans JSON f type etat");
                            e.printStackTrace();
                        }
                        v++;
                        aller(v);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        user.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                4, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(types);


        url=Variable_general.lien_get_commande+"/"+iemi;
        Log.d("kokoko",url);
        Variable_general.commandes.clear();
        StringRequest evenementrequest = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("kokoko",response);
                            Variable_general.json_commandes = new JSONArray(response);
                            for (int i = 0; i < Variable_general.json_commandes.length(); i++)
                            {

                                Commande commande=new Commande();
                                //commande
                                JSONObject com = Variable_general.json_commandes.getJSONObject(i).getJSONObject("Commande");
                                commande.setCommande_description(com.getString("description"));
                                commande.setCommande_etat(com.getString("etat"));
                                commande.setCommande_created(com.getString("created"));
                                //Patient
                                JSONObject patient = Variable_general.json_commandes.getJSONObject(i).getJSONObject("Patient");
                                commande.setPatient_nom(patient.getString("name"));
                                commande.setPatient_age(patient.getString("age"));
                                commande.setPatient_sexe(patient.getString("sexe"));
                                commande.setPatient_visage(patient.getString("typevissage"));
                                //Client
                                JSONObject clientJson = Variable_general.json_commandes.getJSONObject(i).getJSONObject("User");
                                commande.setClient_nom(clientJson.getString("nom"));
                                commande.setClient_tel(clientJson.getString("tel"));
                                commande.setClient_adresse(clientJson.getString("adresse"));
                                commande.setClient_ice(clientJson.getString("ice"));
                                //dents
                                JSONArray dents = Variable_general.json_commandes.getJSONObject(i).getJSONArray("Dent");
                                ArrayList<Dent> dents1 =new ArrayList<Dent>();
                                for(int ii=0;ii<dents.length();ii++)
                                {
                                    JSONObject dent = dents.getJSONObject(ii);
                                    String name=dent.getString("name");
                                    String contourcervical=dent.getString("contourcervical");
                                    String embrasure=dent.getString("embrasure");
                                    String teinte_haut=dent.getString("teinte_haut");
                                    String teinte_bas=dent.getString("teinte_bas");
                                    String produits="";
                                    Dent d=new Dent(name,contourcervical,embrasure,teinte_haut,teinte_bas,produits);
                                    dents1.add(d);
                                }
                                commande.setDents(dents1);
                                //Etats
                                JSONArray etats = Variable_general.json_commandes.getJSONObject(i).getJSONArray("Etat");
                                ArrayList<Etat> etats1 =new ArrayList<Etat>();
                                for(int ii=0;ii<etats.length();ii++)
                                {
                                    JSONObject etat = etats.getJSONObject(ii);
                                    String user=etat.getString("user_id");
                                    String etat1=etat.getString("etat");
                                    String info=etat.getString("info");
                                    String datedebut=etat.getString("created");
                                    String datefin=etat.getString("date_fin");
                                    String jours=etat.getString("jour");
                                    Etat d=new Etat(user,etat1,info,datedebut,datefin,jours);
                                    etats1.add(d);
                                }
                                commande.setEtats(etats1);
                                Variable_general.commandes.add(commande);
                            }

                            Log.d("kokoko", Variable_general.commandes.toString());
                        } catch (JSONException e) {
                            Log.d("koko","Erreur dans JSON");
                            e.printStackTrace();
                        }

                        v++;
                        aller(v);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        evenementrequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                1000, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(evenementrequest);
    }




    public void aller(int v)
    {
        if(intrnet==1 && v==3) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(splashScreen.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    splashScreen.this.startActivity(intent);
                }
            }, 2000);
        }
    }
}
