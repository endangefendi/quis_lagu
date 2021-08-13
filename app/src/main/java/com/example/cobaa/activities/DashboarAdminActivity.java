package com.example.cobaa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.cobaa.R;
import com.example.cobaa.constans.DataPreference;
import com.google.firebase.auth.FirebaseAuth;

public class DashboarAdminActivity extends AppCompatActivity {
    private final String TAG = "DashboarAdminActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboar_admin);
        Log.e(TAG, "onCreate");

        ImageView btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(view -> {
            popupKonfirmasi();
        });
    }

    private void popupKonfirmasi() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi ?")
                .setCancelable(false)
                .setPositiveButton("Keluar",
                        (dialog, id) -> {
                            DataPreference.DELETE_ADMIN(DashboarAdminActivity.this);
                            FirebaseAuth.getInstance().signOut();
                            Intent i=new Intent(getApplicationContext(),LoginAdminActivity.class);
                            startActivity(i);
                            finish();
                        }
                ).setNegativeButton("Batal", (dialogInterface, i) -> dialogInterface.dismiss());
        AlertDialog alert = builder.create();
        alert.show();

        //for positive side button
        alert.getButton(alert.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
    }
}