package com.example.cobaa.activities.admin;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaa.R;
import com.example.cobaa.adapter.BannerAdapter;
import com.example.cobaa.adapter.LaguAdapter;
import com.example.cobaa.models.BannerModel;
import com.example.cobaa.models.LaguModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MasterLaguActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<LaguModel> list;
    private LaguAdapter adapter;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_lagu);
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> finish());
        ImageView iv_add = findViewById(R.id.iv_add);
        iv_add.setOnClickListener(v -> startActivity(new Intent(this, AddLaguActivity.class)));

        recyclerView = findViewById(R.id.list_menu_item);
        list = new ArrayList<>();
        mp = new MediaPlayer();

        adapter = new LaguAdapter(MasterLaguActivity.this, list, mp);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                MasterLaguActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Lagu");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    LaguModel p = dataSnapshot1.getValue(LaguModel.class);
                    list.add(p);
                }

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MasterLaguActivity.this, "Opsss.... Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}