package com.example.cobaa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.cobaa.R;
import com.example.cobaa.constans.DataPreference;

public class SplashScreenAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        final Handler handler=new Handler();
        handler.postDelayed(() -> {
            if (DataPreference.ID != null){
                startActivity(new Intent(getApplicationContext(), IntroPageActivity.class));
                finish();
            }else {
                startActivity(new Intent(getApplicationContext(), LoginAdminActivity.class));
                finish();
            }
        }, 3000L);


    }
}
