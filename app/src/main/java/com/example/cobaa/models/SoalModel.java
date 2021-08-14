package com.example.cobaa.models;

public class SoalModel {
    String dari_daerah, jenis_soal, id, jawaban, lagu, pilihan1, pilihan2, pilihan3, pilihan4, soal;


    public SoalModel(String dari_daerah, String jenis_soal, String id, String jawaban,
                     String lagu, String pilihan1, String pilihan2, String pilihan3, String pilihan4, String soal){
        this.dari_daerah = dari_daerah;
        this.jenis_soal = jenis_soal;
        this.id = id;
        this.jawaban = jawaban;
        this.lagu = lagu;
        this.pilihan1 = pilihan1;
        this.pilihan2 = pilihan2;
        this.pilihan3 = pilihan3;
        this.pilihan4 = pilihan4;
        this.soal = soal;
    }

    public SoalModel(){

    }

    public String getDari_daerah() {
        return dari_daerah;
    }

    public void setDari_daerah(String dari_daerah) {
        this.dari_daerah = dari_daerah;
    }

    public String getJenis_soal() {
        return jenis_soal;
    }

    public void setJenis_soal(String jenis_soal) {
        this.jenis_soal = jenis_soal;
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

    public String getLagu() {
        return lagu;
    }

    public void setLagu(String lagu) {
        this.lagu = lagu;
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
