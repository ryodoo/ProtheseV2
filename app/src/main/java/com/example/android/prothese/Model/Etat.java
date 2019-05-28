package com.example.android.prothese.Model;

public class Etat
{

    private String user;
    private String etat;
    private String info;
    private String datedebut;
    private String datefin;
    private String jour;


    public Etat(String user, String etat, String info, String datedebut, String datefin, String jour) {
        this.user = user;
        this.etat = etat;
        this.info = info;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.jour = jour;
    }


    public Etat()
    {

    }

    @Override
    public String toString() {
        return "Etat{" +
                "user='" + user + '\'' +
                ", etat='" + etat + '\'' +
                ", info='" + info + '\'' +
                ", datedebut='" + datedebut + '\'' +
                ", datefin='" + datefin + '\'' +
                ", jour='" + jour + '\'' +
                '}';
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}
