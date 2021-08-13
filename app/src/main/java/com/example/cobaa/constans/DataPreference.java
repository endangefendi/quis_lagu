package com.fend.corp.shippingcharges.constans;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

public class DataPreference {

    public static final String DATA_CEK = "data_cek";
    public static final String ID_KOTA_KE = "id_kota_ke";
    public static final String ID_KOTA_DARI = "id_kota_dari";
    public static final String ORIGIN = "id_kecamatan_dari";
    public static final String DESTINATION = "id_kecamatan_ke";
    public static final String ORIGINTYPE = "subdistrict";
    public static final String DESTINATIONTYPE = "subdistrict";
    public static final String WEIGHT = "berat";
    public static final String NAMA_KEC_KE = "nama_kecamatan_ke";
    public static final String NAMA_KOTA_KE = "nama_kota_ke";
    public static final String NAMA_KEC_DARI = "nama_kecamatan_dari";
    public static final String NAMA_KOTA_DARI = "nama_kota_dari";

    public static void SAVE_BERAT(Context context, String etWeight){
        SharedPreferences.Editor data_cek = context.getSharedPreferences(DATA_CEK, Context.MODE_PRIVATE).edit();
        data_cek.putString(ORIGINTYPE,"subdistrict");
        data_cek.putString(DESTINATIONTYPE, "subdistrict");
        data_cek.putString(WEIGHT,etWeight);
        data_cek.apply();
    }
    public static void DELETE_BERAT(Context context){
        SharedPreferences.Editor data_cek = context.getSharedPreferences(DATA_CEK, Context.MODE_PRIVATE).edit();
        data_cek.putString(ORIGINTYPE,"subdistrict");
        data_cek.putString(DESTINATIONTYPE, "subdistrict");
        data_cek.putString(WEIGHT,"");
        data_cek.apply();
    }

    public static void SAVE_KECAMATAN_DARI(Context context, String nama_kecamatan_dari, String id_kecamatan_dari){
        SharedPreferences.Editor data_cek = context.getSharedPreferences(DATA_CEK, Context.MODE_PRIVATE).edit();
        data_cek.putString(NAMA_KEC_DARI,nama_kecamatan_dari);
        data_cek.putString(ORIGIN, id_kecamatan_dari);
        data_cek.apply();
    }

    public static void SAVE_KECAMATAN_KE(Context context, String nama_kecamatan_ke, String id_kecamatan_ke){
        SharedPreferences.Editor data_cek = context.getSharedPreferences(DATA_CEK, Context.MODE_PRIVATE).edit();
        data_cek.putString(NAMA_KEC_KE,nama_kecamatan_ke);
        data_cek.putString(DESTINATION, id_kecamatan_ke);
        data_cek.apply();
    }
    public static void SAVE_KOTA_DARI(Context context, String nama_kota_dari, String id_kota_dari){
        SharedPreferences.Editor data_cek = context.getSharedPreferences(DATA_CEK, Context.MODE_PRIVATE).edit();
        data_cek.putString(NAMA_KOTA_DARI,nama_kota_dari);
        data_cek.putString(ID_KOTA_DARI, id_kota_dari);
        data_cek.apply();
    }

    public static void SAVE_KOTA_KE(Context context, String nama_kota_ke, String id_kota_ke){
        SharedPreferences.Editor data_cek = context.getSharedPreferences(DATA_CEK, Context.MODE_PRIVATE).edit();
        data_cek.putString(NAMA_KOTA_KE,nama_kota_ke);
        data_cek.putString(ID_KOTA_KE, id_kota_ke);
        data_cek.apply();
    }

    public static void FIRST_SET_TEXT(Context context, TextView etFromCity, TextView etToCity, TextView etWeight){
        String dari;
        String ke;
        String berat ;
        SharedPreferences data_cek = context.getSharedPreferences(DATA_CEK, Context.MODE_PRIVATE);
        dari = data_cek.getString(NAMA_KOTA_DARI, "") +" - " + data_cek.getString(NAMA_KEC_DARI,"");
        ke = data_cek.getString(NAMA_KOTA_KE, "") +" - " + data_cek.getString(NAMA_KEC_KE,"");

        berat = data_cek.getString(WEIGHT,"");
        etToCity.setText(ke);
        etFromCity.setText(dari);
        etWeight.setText(berat);
    }

    public static void clearData(Context context, TextView etFromCity, TextView etToCity, TextView etWeight){
        SharedPreferences.Editor data_cek = context.getSharedPreferences(DATA_CEK, Context.MODE_PRIVATE).edit();
        data_cek.putString(ID_KOTA_KE,"");
        data_cek.putString(ID_KOTA_DARI,"");
        data_cek.putString(ORIGIN,"");
        data_cek.putString(DESTINATION,"");
        data_cek.putString(ORIGINTYPE,"");
        data_cek.putString(DESTINATIONTYPE,"");
        data_cek.putString(WEIGHT,"");
        data_cek.putString(NAMA_KEC_KE,"");
        data_cek.putString(NAMA_KOTA_KE,"");
        data_cek.putString(NAMA_KEC_DARI,"");
        data_cek.putString(NAMA_KOTA_DARI,"");
        data_cek.apply();
        etToCity.setText("");
        etFromCity.setText("");
        etWeight.setText("");
    }

}
