package com.oreo7.mapamiejsc;



public class KategoriaModel{
    private int id;
    private String name;

    public KategoriaModel(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "KategoriaModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
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





}



