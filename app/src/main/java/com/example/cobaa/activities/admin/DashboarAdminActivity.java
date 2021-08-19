package com.example.cobaa.activities.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cobaa.R;
import com.example.cobaa.adapter.MenuAdminAdapter;
import com.example.cobaa.constans.DataPreference;
import com.example.cobaa.models.MenuModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboarAdminActivity extends AppCompatActivity {
    private final String TAG = "DashboarAdminActivity";

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<MenuModel> list;
    MenuAdminAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboar_admin);
        Log.e(TAG, "onCreate");

        ImageView btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(view -> popupKonfirmasi());

        recyclerView = findViewById(R.id.list_menu_item);
        list = new ArrayList<>();

        adapter = new MenuAdminAdapter(DashboarAdminActivity.this,list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                DashboarAdminActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        reference = FirebaseDatabase.getInstance().getReference("menu admin");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    MenuModel p = dataSnapshot1.getValue(MenuModel.class);
                    list.add(p);
                }

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "databaseError : " + databaseError.getMessage());

                Toast.makeText(DashboarAdminActivity.this, "Opsss.... Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
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
                            Intent i=new Intent(getApplicationContext(), LoginAdminActivity.class);
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