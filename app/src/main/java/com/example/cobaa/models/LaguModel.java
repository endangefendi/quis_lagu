package com.example.cobaa.models;

public class LaguModel {
    String id;
    String url;
    String nama;

    public LaguModel(String id,
            String url,
            String nama){
        this.id = id;
        this.url = url;
        this.nama = nama;
    }

    public LaguModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
