package com.example.cobaa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cobaa.R;
import com.example.cobaa.StartGameActivity;

public class IntroPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(IntroPageActivity.this, StartGameActivity.class);
            startActivity(intent);
        });

        ImageView btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v -> {
            Intent intent = new Intent(IntroPageActivity.this, LoginAdminActivity.class);
            startActivity(intent);
            finish();
        });


    }
}