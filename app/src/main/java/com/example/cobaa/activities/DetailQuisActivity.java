package com.example.cobaa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cobaa.R;

public class DetailQuisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_quis);
        TextView text = findViewById(R.id.text);
        if (getIntent()!=null){
            String game = getIntent().getStringExtra("menu");
            text.setText(game+"\n"+text.getText());
        }
    }
}