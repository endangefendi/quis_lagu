package com.example.cobaa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.cobaa.R;

import static com.example.cobaa.constans.DataPreference.DATA_ADMIN;
import static com.example.cobaa.constans.DataPreference.ID;

public class SplashScreenAcitivity extends AppCompatActivity {
    private final String TAG = "SplashScreenAcitivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        SharedPreferences data_cek = this.getSharedPreferences(DATA_ADMIN, Context.MODE_PRIVATE);
        String id = data_cek.getString(ID,"");
        Log.e(TAG, id);
        final Handler handler=new Handler();
        handler.postDelayed(() -> {
            if (id.equalsIgnoreCase("")){
                startActivity(new Intent(getApplicationContext(), IntroPageActivity.class));
                finish();
            }else {
                startActivity(new Intent(getApplicationContext(), DashboarAdminActivity.class));
                finish();
            }
        }, 3000L);


    }
}
