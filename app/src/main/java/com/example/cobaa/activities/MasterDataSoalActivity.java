package com.example.cobaa.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobaa.R;
import com.example.cobaa.adapter.SoalAdapter;
import com.example.cobaa.models.SoalModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MasterDataSoalActivity extends AppCompatActivity {
    private final String TAG = "MasterDataSoal";

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<SoalModel> list;
    private MediaPlayer mp;
    private SoalAdapter adapter;

    private String key_soal;
    private String jenis_soal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_data_soal);

        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> finish());

        TextView tv_title = findViewById(R.id.tv_title);

        final Bundle bun = this.getIntent().getExtras();
        if (bun != null) {
            key_soal = bun.getString("key_menu");
            tv_title.setText(key_soal);
        }

        if (key_soal.toLowerCase().contains("map")){
            jenis_soal = "map";
        }else if (key_soal.toLowerCase().contains("random")){
            jenis_soal = "random";
        }

        ImageView add = findViewById(R.id.iv_add);
        add.setOnClickListener(view -> {
            if (jenis_soal == "random"){
                startActivity(new Intent(MasterDataSoalActivity.this, TambahSoalRandomActivity.class));
            }
            else {
//                startActivity(new Intent(MasterDataSoalActivity.this, MAPTambahSoalctivity.class));
                Toast.makeText(MasterDataSoalActivity.this, "KLIK Tambah Soal Map", Toast.LENGTH_SHORT).show();

            }
        });

        recyclerView = findViewById(R.id.list_menu_item);
        list = new ArrayList<>();
        mp = new MediaPlayer();

        adapter = new SoalAdapter(MasterDataSoalActivity.this, list, mp, key_soal);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                MasterDataSoalActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        reference = FirebaseDatabase.getInstance().getReference("soal");
        reference.orderByChild("jenis_soal").equalTo(jenis_soal).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    SoalModel p = dataSnapshot1.getValue(SoalModel.class);
                    list.add(p);
                }

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MasterDataSoalActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }



}