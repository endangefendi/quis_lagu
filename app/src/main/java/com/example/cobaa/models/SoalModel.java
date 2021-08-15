package com.example.cobaa.models;

public class SoalModel {
    String id;
    String jawaban;
    String jenis_soal;
    String lagu;
    String map;
    String pilihan1;
    String pilihan2;
    String pilihan3;
    String pilihan4;
    String soal;

    public SoalModel(String id,
            String jawaban,
            String jenis_soal,
            String lagu,
            String map,
            String pilihan1,
            String pilihan2,
            String pilihan3,
            String pilihan4,
            String soal){
        this.id = id;
        this.jawaban = jawaban;
        this.jenis_soal = jenis_soal;
        this.lagu = lagu;
        this.map = map;
        this.pilihan1 = pilihan1;
        this.pilihan2 = pilihan2;
        this.pilihan3 = pilihan3;
        this.pilihan4 = pilihan4;
        this.soal = soal;
    }

    public SoalModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getJenis_soal() {
        return jenis_soal;
    }

    public void setJenis_soal(String jenis_soal) {
        this.jenis_soal = jenis_soal;
    }

    public String getLagu() {
        return lagu;
    }

    public void setLagu(String lagu) {
        this.lagu = lagu;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getPilihan1() {
        return pilihan1;
    }

    public void setPilihan1(String pilihan1) {
        this.pilihan1 = pilihan1;
    }

    public String getPilihan2() {
        return pilihan2;
    }

    public void setPilihan2(String pilihan2) {
        this.pilihan2 = pilihan2;
    }

    public String getPilihan3() {
        return pilihan3;
    }

    public void setPilihan3(String pilihan3) {
        this.pilihan3 = pilihan3;
    }

    public String getPilihan4() {
        return pilihan4;
    }

    public void setPilihan4(String pilihan4) {
        this.pilihan4 = pilihan4;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }
}
