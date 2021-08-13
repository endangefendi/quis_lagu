package com.example.cobaa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.cobaa.R;

public class DashboarAdminActivity extends AppCompatActivity {
    private final String TAG = "DashboarAdminActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboar_admin);
        Log.e(TAG, "onCreate");

    }
}