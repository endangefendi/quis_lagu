package com.example.cobaa.activities.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_data_soal);

        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> onBackPressed());
        TextView no_item = findViewById(R.id.no_item);

        TextView tv_title = findViewById(R.id.tv_title);


        ImageView add = findViewById(R.id.iv_add);
        add.setOnClickListener(view -> {
            mp.stop();
            mp.reset();
            startActivity(new Intent(MasterDataSoalActivity.this, TambahSoalMapActivity.class));
        });

        recyclerView = findViewById(R.id.list_menu_item);
        list = new ArrayList<>();
        mp = new MediaPlayer();

        adapter = new SoalAdapter(MasterDataSoalActivity.this, list, mp);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                MasterDataSoalActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        reference = FirebaseDatabase.getInstance().getReference("soal");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    SoalModel p = dataSnapshot1.getValue(SoalModel.class);
                    list.add(p);
                }

                recyclerView.setAdapter(adapter);
                if (list.size()<=0){
                    no_item.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    no_item.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MasterDataSoalActivity.this, "Opsss.... Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        mp.stop();
        mp.reset();
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}