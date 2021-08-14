package com.example.cobaa.models;

public class MenuModel {
    String id;
    String name_menu;

    public MenuModel(String id, String name_menu){
        this.id = id;
        this.name_menu = name_menu;
    }

    public MenuModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_menu() {
        return name_menu;
    }

    public void setName_menu(String name_menu) {
        this.name_menu = name_menu;
    }
}
