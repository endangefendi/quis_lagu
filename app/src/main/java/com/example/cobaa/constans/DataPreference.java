package com.example.cobaa.constans;

import android.content.Context;
import android.content.SharedPreferences;

public class DataPreference {

    public static final String DATA_ADMIN = "data_admin";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";

    public static void SAVE_ADMIN(Context context, String id, String username, String password, String name){
        SharedPreferences.Editor data_cek = context.getSharedPreferences(DATA_ADMIN, Context.MODE_PRIVATE).edit();
        data_cek.putString(ID,id);
        data_cek.putString(USERNAME, username);
        data_cek.putString(PASSWORD,password);
        data_cek.putString(NAME, name);
        data_cek.apply();
    }

    public static void DELETE_ADMIN(Context context){
        SharedPreferences.Editor data_cek = context.getSharedPreferences(DATA_ADMIN, Context.MODE_PRIVATE).edit();
        data_cek.putString(ID,"");
        data_cek.putString(USERNAME, "");
        data_cek.putString(PASSWORD,"");
        data_cek.putString(NAME, "");
        data_cek.apply();
    }


}
