package com.example.cobaa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaa.activities.DetailQuisActivity;
import com.example.cobaa.activities.QuisTidakAcakActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);
        Button btnMap = findViewById(R.id.btnMap);
        Button btnRandom = findViewById(R.id.btnRandom);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        btnMap.setOnClickListener(v -> {
            mAuth.signInAnonymously()
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            Log.e("TAG", "signInAnonymously: "+user.getUid());
                            Intent intent = new Intent(StartGameActivity.this, QuisTidakAcakActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("TAG", "signInAnonymously:failure", task.getException());
                        }
                    });
        });

        btnRandom.setOnClickListener(v -> {
            mAuth.signInAnonymously()
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            Log.e("TAG", "signInAnonymously: "+user.getUid());
                            Intent intent = new Intent(StartGameActivity.this, DetailQuisActivity.class);
                            intent.putExtra("jenis_soal","random");
                            intent.putExtra("map","");
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("TAG", "signInAnonymously:failure", task.getException());
                        }
                    });
        });

    }


}