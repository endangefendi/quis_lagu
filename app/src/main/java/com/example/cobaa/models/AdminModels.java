package com.example.cobaa.models;


public class AdminModels {
    String id;
    String username;
    String password;
    String nama;

    public AdminModels(String id, String username, String password, String nama) {
        this.id=id;
        this.username=username;
        this.password=password;
        this.nama=nama;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
