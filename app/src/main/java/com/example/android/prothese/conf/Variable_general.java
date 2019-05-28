package com.example.android.prothese.conf;

import com.example.android.prothese.Model.Commande;
import com.example.android.prothese.Model.Dent;
import com.example.android.prothese.Model.Typeetat;
import com.example.android.prothese.Model.User;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 28/02/2019.
 */

public class Variable_general {
    public static int version_base=28;
    public static String name_base="icoz";
    public static int accuil=0;
    public static String iemi="0";
    //-----------------------------------------Data---------------------------------------------
    public static JSONArray json_user;
    public static JSONArray json_commandes=null;
    public static ArrayList<Commande> commandes=new ArrayList<Commande>();
    public static String user;
    public static JSONArray json_typeetats;
    public static ArrayList<Typeetat> typeetats=new ArrayList<Typeetat>();

    //--------------------------Lien pour recevoir des données----------------------
    public static String domaine="http://prothese.icoz.ma/apimobile";
    public static String lien_get_user=domaine+"/getuser/";
    public static String lien_get_commande=domaine+"/get_commandes/";
    public static String lien_entrer=domaine+"/entrer/";
    public static String lien_recherche=domaine+"/recherche/";
    public static String getLien_get_typeetats=domaine+"/gettypeetats/";

    //-------------------------------------Lien d'envoie des données--------------------//
    public static String envoi_lien_login=domaine+"/login";
    public static String lien_sortie=domaine+"/sortie/";


    //aSuprimer
    public static ArrayList<Dent> dents=new ArrayList<Dent>();
}
