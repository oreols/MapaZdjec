package com.oreo7.mapamiejsc;



public class KategoriaModel{
    private int id;
    private String name;
    private String opis;

    public KategoriaModel(int id, String name, String opis){
        this.id = id;
        this.name = name;
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "KategoriaModel{" +
                "id=" + id +
                ", name='" + name +
                "opis=" + opis + '\'' +
                '}';
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getOpis(){
        return opis;
    }
    public void setOpis(String opis){
        this.opis = opis;
    }



}



