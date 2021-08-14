package com.example.cobaa.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cobaa.R;
import com.example.cobaa.adapter.BannerAdapter;
import com.example.cobaa.adapter.PulauAdapter;
import com.example.cobaa.models.BannerModel;
import com.example.cobaa.models.PulauModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MasterDataPulauActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<PulauModel> list;
    private PulauAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pulau);

        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> finish());
        ImageView iv_add = findViewById(R.id.iv_add);
        iv_add.setOnClickListener(v -> startActivity(new Intent(this, AddPulauActivity.class)));
        recyclerView = findViewById(R.id.list_menu_item);
        list = new ArrayList<>();
        adapter = new PulauAdapter(MasterDataPulauActivity.this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                MasterDataPulauActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("master pulau");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    PulauModel p = dataSnapshot1.getValue(PulauModel.class);
                    list.add(p);
                }

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MasterDataPulauActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}