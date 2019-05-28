package com.example.android.prothese.Model;

public class Typeetat
{
    public String id;
    public String name;


    public Typeetat()
    {

    }

    public Typeetat(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Typeetat{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
