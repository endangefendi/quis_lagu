package com.fend.temanmuslim.model;


public class Users {
    String token;
    int versi_code_program;
    String android_id;
    String manufacturer;
    String model;
    String version_android;
    String time;
    String uid;

    public Users(String uid, String token, int versi_code_program, String android_id, String manufacturer, String model, String version_android, String time) {
        this.uid=uid;
        this.token=token;
        this.versi_code_program=versi_code_program;
        this.android_id=android_id;
        this.manufacturer=manufacturer;
        this.model=model;
        this.version_android=version_android;
        this.time=time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getVersi_code_program() {
        return versi_code_program;
    }

    public void setVersi_code_program(int versi_code_program) {
        this.versi_code_program = versi_code_program;
    }

    public String getVersion_android() {
        return version_android;
    }

    public void setVersion_android(String version_android) {
        this.version_android = version_android;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

}
