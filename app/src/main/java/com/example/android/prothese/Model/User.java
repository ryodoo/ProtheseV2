package com.example.android.prothese.Model;

public class User
{
    public String id;
    public String nom;

    public User(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public User()
    {

    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}
