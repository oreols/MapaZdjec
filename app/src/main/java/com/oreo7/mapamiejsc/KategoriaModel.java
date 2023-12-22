package com.oreo7.mapamiejsc;



public class KategoriaModel{
    private long id;
    private String name;
    private String opis;

    public KategoriaModel(long id, String name, String opis){
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


    public long getId(){
        return id;
    }
    public void setId(long id){
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



