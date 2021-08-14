package com.example.cobaa.models;

public class BannerModel {
    String id;
    String name_banner;
    String banner;

    public BannerModel(String id, String banner, String name_banner){
        this.id = id;
        this.banner = banner;
        this.name_banner = name_banner;
    }

    public BannerModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_banner() {
        return name_banner;
    }

    public void setName_banner(String name_banner) {
        this.name_banner = name_banner;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
