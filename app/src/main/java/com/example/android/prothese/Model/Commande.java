package com.example.android.prothese.Model;

import java.util.ArrayList;

public class Commande
{
    public String client_nom;
    public String client_tel;
    public String client_adresse;
    public String client_ice;

    public String commande_description;
    public String commande_etat;
    public String commande_created;

    public String patient_nom;
    public String patient_age;
    public String patient_sexe;
    public String patient_visage;
    public ArrayList<Dent> dents;
    public ArrayList<Etat> etats;


    public Commande() {
    }

    public Commande(String client_nom, String client_tel, String client_adresse, String client_ice, String commande_description, String commande_etat, String commande_created, String patient_nom, String patient_age, String patient_sexe, String patient_visage, ArrayList<Dent> dents, ArrayList<Etat> etats) {
        this.client_nom = client_nom;
        this.client_tel = client_tel;
        this.client_adresse = client_adresse;
        this.client_ice = client_ice;
        this.commande_description = commande_description;
        this.commande_etat = commande_etat;
        this.commande_created = commande_created;
        this.patient_nom = patient_nom;
        this.patient_age = patient_age;
        this.patient_sexe = patient_sexe;
        this.patient_visage = patient_visage;
        this.dents = dents;
        this.etats = etats;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "client_nom='" + client_nom + '\'' +
                ", client_tel='" + client_tel + '\'' +
                ", client_adresse='" + client_adresse + '\'' +
                ", client_ice='" + client_ice + '\'' +
                ", commande_description='" + commande_description + '\'' +
                ", commande_etat='" + commande_etat + '\'' +
                ", commande_created='" + commande_created + '\'' +
                ", patient_nom='" + patient_nom + '\'' +
                ", patient_age='" + patient_age + '\'' +
                ", patient_sexe='" + patient_sexe + '\'' +
                ", patient_visage='" + patient_visage + '\'' +
                ", dents=" + dents +
                ", etats=" + etats +
                '}';
    }

    public ArrayList<Etat> getEtats() {
        return etats;
    }

    public void setEtats(ArrayList<Etat> etats) {
        this.etats = etats;
    }

    public String getClient_nom() {
        return client_nom;
    }

    public void setClient_nom(String client_nom) {
        this.client_nom = client_nom;
    }

    public String getClient_tel() {
        return client_tel;
    }

    public void setClient_tel(String client_tel) {
        this.client_tel = client_tel;
    }

    public String getClient_adresse() {
        return client_adresse;
    }

    public void setClient_adresse(String client_adresse) {
        this.client_adresse = client_adresse;
    }

    public String getClient_ice() {
        return client_ice;
    }

    public void setClient_ice(String client_ice) {
        this.client_ice = client_ice;
    }

    public String getCommande_description() {
        return commande_description;
    }

    public void setCommande_description(String commande_description) {
        this.commande_description = commande_description;
    }

    public String getCommande_etat() {
        return commande_etat;
    }

    public void setCommande_etat(String commande_etat) {
        this.commande_etat = commande_etat;
    }

    public String getCommande_created() {
        return commande_created;
    }

    public void setCommande_created(String commande_created) {
        this.commande_created = commande_created;
    }

    public String getPatient_nom() {
        return patient_nom;
    }

    public void setPatient_nom(String patient_nom) {
        this.patient_nom = patient_nom;
    }

    public String getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_sexe() {
        return patient_sexe;
    }

    public void setPatient_sexe(String patient_sexe) {
        this.patient_sexe = patient_sexe;
    }

    public String getPatient_visage() {
        return patient_visage;
    }

    public void setPatient_visage(String patient_visage) {
        this.patient_visage = patient_visage;
    }

    public ArrayList<Dent> getDents() {
        return dents;
    }

    public void setDents(ArrayList<Dent> dents) {
        this.dents = dents;
    }
}
