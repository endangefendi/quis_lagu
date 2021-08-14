package com.example.cobaa.models;

public class PulauModel {
    String id;
    String name_pulau;
    String icon;

    public PulauModel(String id, String name_pulau, String icon){
        this.id = id;
        this.name_pulau = name_pulau;
        this.icon = icon;
    }

    public PulauModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_pulau() {
        return name_pulau;
    }

    public void setName_pulau(String name_pulau) {
        this.name_pulau = name_pulau;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
