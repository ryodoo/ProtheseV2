package com.example.android.prothese.Model;

public class Dent {

    public String name;
    public String contourcervical;
    public String embrasure;
    public String teinte_haut;
    public String teinte_bas;
    public String produits;


    public Dent(String name, String contourcervical, String embrasure, String teinte_haut, String teinte_bas, String produits) {
        this.name = name;
        this.contourcervical = contourcervical;
        this.embrasure = embrasure;
        this.teinte_haut = teinte_haut;
        this.teinte_bas = teinte_bas;
        this.produits = produits;
    }

    public Dent()
    {

    }

    @Override
    public String toString() {
        return "Dent{" +
                "name='" + name + '\'' +
                ", contourcervical='" + contourcervical + '\'' +
                ", embrasure='" + embrasure + '\'' +
                ", teinte_haut='" + teinte_haut + '\'' +
                ", teinte_bas='" + teinte_bas + '\'' +
                ", produits='" + produits + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContourcervical() {
        return contourcervical;
    }

    public void setContourcervical(String contourcervical) {
        this.contourcervical = contourcervical;
    }

    public String getEmbrasure() {
        return embrasure;
    }

    public void setEmbrasure(String embrasure) {
        this.embrasure = embrasure;
    }

    public String getTeinte_haut() {
        return teinte_haut;
    }

    public void setTeinte_haut(String teinte_haut) {
        this.teinte_haut = teinte_haut;
    }

    public String getTeinte_bas() {
        return teinte_bas;
    }

    public void setTeinte_bas(String teinte_bas) {
        this.teinte_bas = teinte_bas;
    }

    public String getProduits() {
        return produits;
    }

    public void setProduits(String produits) {
        this.produits = produits;
    }
}
