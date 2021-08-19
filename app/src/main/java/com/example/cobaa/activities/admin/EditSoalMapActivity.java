package com.example.cobaa.activities.admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaa.R;
import com.example.cobaa.adapter.DaerahAdapter;
import com.example.cobaa.models.PulauModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditSoalMapActivity extends AppCompatActivity {
    private final String TAG = "EditSoalMapActivity";
    LinearLayout frame_edit;
    ImageView btn_edit;

    private ProgressDialog progressDialog;

    EditText txt_soal, txt_jawaban1, txt_jawaban2, txt_jawaban3, txt_jawaban4, txt_jawaban_benar, txt_daerah;
    ImageView _daerah;
    ImageView btnStart ;
    ImageView btnStop;
    private MediaPlayer mp;

    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_soal_map);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(v -> finish());

        frame_edit = findViewById(R.id.frame_edit);

        txt_soal = findViewById(R.id.txt_soal);
        txt_jawaban1 = findViewById(R.id.txt_jawaban1);
        txt_jawaban2 = findViewById(R.id.txt_jawaban2);
        txt_jawaban3 = findViewById(R.id.txt_jawaban3);
        txt_jawaban4 = findViewById(R.id.txt_jawaban4);
        txt_jawaban_benar = findViewById(R.id.txt_jawaban_benar);
        txt_daerah = findViewById(R.id.txt_daerah);
        _daerah = findViewById(R.id._daerah);
        _daerah.setOnClickListener(v -> popupdaerah(txt_daerah));

        Button btn_save = findViewById(R.id.btn_add);
        btn_save.setOnClickListener(v -> validasi());

        btn_edit = findViewById(R.id.iv_edit);
        btn_edit.setOnClickListener(v -> edit());
        setDataawal();
        disable();
        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(v -> batal());

        mp = new MediaPlayer();
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnStart.setOnClickListener(view -> {
            if (lagu.contains("https://firebasestorage.googleapis.com")) {
                if (!isPlaying) {
                    play_audio(lagu);
                    isPlaying = true;
                    btnStart.setVisibility(View.GONE);
                    btnStop.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(EditSoalMapActivity.this,
                        "Opsss.... Lagu tidak dapat diputar", Toast.LENGTH_LONG).show();
            }
        });

        btnStop.setOnClickListener(view -> {
            if (isPlaying) {
                mp.stop();
                mp.reset();
                isPlaying = false;
                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.GONE);
            }
        });

    }

    private final List<PulauModel> listDaerah = new ArrayList<>();
    private ListView mListView;
    DaerahAdapter adapter_daerah;
    private void popupdaerah (final EditText editText) {
        AlertDialog.Builder alert;
        AlertDialog ad;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        assert inflater != null;
        View alertLayout = inflater.inflate(R.layout.dialog_daerah, null);

        alert = new AlertDialog.Builder(this);
        alert.setTitle("Pilih Daerah ");
        alert.setIcon(R.drawable.ic_logo);
        alert.setView(alertLayout);
        alert.setCancelable(true);

        ad = alert.show();

        mListView = alertLayout.findViewById(R.id.listItem);

        listDaerah.clear();
        adapter_daerah = new DaerahAdapter(this, listDaerah);

        mListView.setClickable(true);

        mListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Object o = mListView.getItemAtPosition(i);
            PulauModel cn = (PulauModel) o;
            ad.dismiss();
            editText.setError(null);
            editText.setText(cn.getName_pulau());
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sedang menyiapkan..");
        progressDialog.show();

        getDaerah();

    }

    private void getDaerah() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("master pulau");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDaerah.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    PulauModel p = dataSnapshot1.getValue(PulauModel.class);
                    listDaerah.add(p);
                }
                mListView.setAdapter(adapter_daerah);
                adapter_daerah.setList(listDaerah);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "databaseError : " + databaseError.getMessage());

                Toast.makeText(EditSoalMapActivity.this, "Opsss.... Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void edit() {
        if (isPlaying) {
            mp.stop();
            mp.reset();
            isPlaying = false;
            btnStart.setVisibility(View.VISIBLE);
            btnStop.setVisibility(View.GONE);
        }

        frame_edit.setVisibility(View.VISIBLE);
        btn_edit.setVisibility(View.GONE);

        txt_soal.setEnabled(true);
        txt_jawaban_benar.setEnabled(true);
        txt_jawaban1.setEnabled(true);
        txt_jawaban2.setEnabled(true);
        txt_jawaban3.setEnabled(true);
        txt_jawaban4.setEnabled(true);
        _daerah.setClickable(true);

    }

    private void disable() {
        frame_edit.setVisibility(View.GONE);
        btn_edit.setVisibility(View.VISIBLE);

        txt_soal.setEnabled(false);
        txt_jawaban_benar.setEnabled(false);
        txt_jawaban1.setEnabled(false);
        txt_jawaban2.setEnabled(false);
        txt_jawaban3.setEnabled(false);
        txt_jawaban4.setEnabled(false);
    }

    private void batal() {
        txt_soal.setText("");
        txt_jawaban_benar.setText("");
        txt_jawaban1.setText("");
        txt_jawaban2.setText("");
        txt_jawaban3.setText("");
        txt_jawaban4.setText("");
        finish();
    }

    String id ="", jenis_soal ="", lagu ="",map = "";
    private void setDataawal() {
        final Bundle bun = this.getIntent().getExtras();
        if (bun != null) {
            id =  bun.getString("id");
            jenis_soal =  bun.getString("jenis_soal");
            lagu =  bun.getString("lagu");
            map =  bun.getString("map");
            txt_soal.setText(bun.getString("soal"));
            txt_jawaban_benar.setText(bun.getString("jawaban"));
            txt_jawaban1.setText(bun.getString("pilihan1"));
            txt_jawaban2.setText(bun.getString("pilihan2"));
            txt_jawaban3.setText(bun.getString("pilihan3"));
            txt_jawaban4.setText(bun.getString("pilihan4"));
            txt_daerah.setText(bun.getString("map"));
            _daerah.setClickable(false);
        }
    }

    private void validasi() {
        String soal = txt_soal.getText().toString().trim();
        String jawaban_benar = txt_jawaban_benar.getText().toString().trim();
        String jawaban1 = txt_jawaban1.getText().toString().trim();
        String jawaban2 = txt_jawaban2.getText().toString().trim();
        String jawaban3 = txt_jawaban3.getText().toString().trim();
        String jawaban4 = txt_jawaban4.getText().toString().trim();
        String daerah = txt_daerah.getText().toString().trim();

        if(soal.isEmpty() && jawaban_benar.isEmpty() && jawaban1.isEmpty() && jawaban2.isEmpty() && jawaban3.isEmpty()
                && jawaban4.isEmpty()){
            Snackbar.make(txt_soal, "Data belum lengkap", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if(soal.isEmpty()){
            txt_soal.setError( "Soal tidak boleh kosong");
            return;
        }

        if(jawaban_benar.isEmpty()){
            txt_jawaban_benar.setError( "Kunci Jawaban tidak boleh kosong");
            return;
        }

        if(jawaban1.isEmpty()){
            txt_jawaban1.setError( "Pilihan Jawaban 1 tidak boleh kosong");
            return;
        }
        if(jawaban2.isEmpty()){
            txt_jawaban2.setError( "Pilihan Jawaban 2 tidak boleh kosong");
            return;
        }
        if(jawaban3.isEmpty()){
            txt_jawaban3.setError( "Pilihan Jawaban 3 tidak boleh kosong");
            return;
        }
        if(jawaban4.isEmpty()){
            txt_jawaban4.setError( "Pilihan Jawaban 4 tidak boleh kosong");
            return;
        }

        if(daerah.isEmpty()){
            txt_daerah.setError( "Asal daerah tidak boleh kosong");
            return;
        }

        saving(soal, jawaban_benar, jawaban1, jawaban2, jawaban3, jawaban4, daerah);
    }

    private void saving(String soal, String jawaban_benar, String jawaban1, String jawaban2, String jawaban3, String jawaban4, String daerah) {
        if (id != null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Menyimpan Data");
            progressDialog.show();
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("soal");
            try {
                ref.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ref.child(id).child("soal").setValue(soal);
                        ref.child(id).child("jawaban").setValue(jawaban_benar);
                        ref.child(id).child("pilihan1").setValue(jawaban1);
                        ref.child(id).child("pilihan2").setValue(jawaban2);
                        ref.child(id).child("pilihan3").setValue(jawaban3);
                        ref.child(id).child("pilihan4").setValue(jawaban4);
                        ref.child(id).child("map").setValue(daerah);

                        progressDialog.dismiss();
                        Toast.makeText(EditSoalMapActivity.this,
                                "Edit Data successfully", Toast.LENGTH_SHORT).show();
                        disable();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Pastikan semua data sudah benar",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void play_audio(final String audio) {
        try {
            mp.setDataSource(audio);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mp.prepareAsync();
        mp.setOnPreparedListener(MediaPlayer::start);
        mp.setOnCompletionListener(mediaPlayer -> {
            mediaPlayer.stop();
            mediaPlayer.reset();
            isPlaying = false;
            btnStart.setVisibility(View.VISIBLE);
            btnStop.setVisibility(View.GONE);
        });
    }

}