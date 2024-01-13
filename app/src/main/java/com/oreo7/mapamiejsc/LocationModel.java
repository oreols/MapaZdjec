package com.oreo7.mapamiejsc;

public class LocationModel {
    private long id;
    private String nazwa;
    private double latitude;
    private double longitude;

    public LocationModel(long id, String nazwa, double latitude, double longitude){
        this.id = id;
        this.nazwa = nazwa;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @Override
    public String toString() {
        return nazwa;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNazwa(){
        return nazwa;
    }

    public void setNazwa(String nazwa){
        this.nazwa = nazwa;
    }

    public double getLatitude(){
        return latitude;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLongitude(){
        return longitude;
    }
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

}
