package com.example.cobaa.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cobaa.MapActivity;
import com.example.cobaa.QuestionActivity;
import com.example.cobaa.R;
import com.example.cobaa.StartGameActivity;
import com.example.cobaa.models.SoalModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class DetailQuisActivity extends AppCompatActivity {

    private TextView tvScore;
    private ImageView btnStart;
    private ImageView btnStop;
    private SeekBar playerSeekBar;
    private TextView tvQuestion;
    private Button btnAnswerA;
    private Button btnAnswerB;
    private Button btnAnswerC;
    private Button btnAnswerD;

    private int no = 1 ;
    private String jawab = "" ;
    private String lagu = "" ;
    private int hasil_jawaban = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_quis);

        insial();
        if (getIntent()!=null){
            String jenis_soal = getIntent().getStringExtra("jenis_soal");
            String map = getIntent().getStringExtra("map");
            getSoal(jenis_soal, map);
        }

        btnAnswerA.setOnClickListener(view -> {
            if (btnAnswerA.getText().toString().equals(jawab)) {
                no++;
                hasil_jawaban++;
                tvScore.setText("Soal : "+no + " / 10" );
                updateQuestion(list.size());
            }else {
                no++;
                tvScore.setText("Soal : "+no + " / 10" );
                updateQuestion(list.size());
            }
        });

        btnAnswerB.setOnClickListener(view -> {
            if (btnAnswerB.getText().toString().equals(jawab)) {
                no++;
                hasil_jawaban++;
                tvScore.setText("Soal : "+no + " / 10" );;
                updateQuestion(list.size());
            }else {
                no++;
                tvScore.setText("Soal : "+no + " / 10" );
                updateQuestion(list.size());
            }
        });

        btnAnswerC.setOnClickListener(view -> {
            if (btnAnswerC.getText().toString().equals(jawab)) {
                no++;
                hasil_jawaban++;
                tvScore.setText("Soal : "+no + " / 10" );
                updateQuestion(list.size());
            }else {
                no++;
                tvScore.setText("Soal : "+no + " / 10" );
                updateQuestion(list.size());
            }
        });

        btnAnswerD.setOnClickListener(view -> {
            if (btnAnswerD.getText().toString().equals(jawab)) {
                no++;
                hasil_jawaban++;
                tvScore.setText("Soal : "+no + " / 10" );
                updateQuestion(list.size());
            }else {
                no++;
                tvScore.setText("Soal : "+no + " / 10" );
                updateQuestion(list.size());
            }
        });

        btnStart.setOnClickListener(v -> playAudio(lagu));
        btnStop.setOnClickListener(v -> stopAudio());
    }



    private ArrayList<SoalModel> list = new ArrayList<>();
    private void getSoal(String jenis_soal, String map) {
        list.clear();
        Log.e("getSoal", jenis_soal+ " || " +map);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("soal");
        reference.orderByChild("jenis_soal").equalTo(jenis_soal).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    SoalModel p = dataSnapshot1.getValue(SoalModel.class);
                    if (p.getMap().toLowerCase().equalsIgnoreCase(map)) {
                        list.add(p);
                    }
                }

                Log.e("list.size()", "" + (list.size()));
                Log.e("list.size()", "" + (list.size()));
                if (list.size()<=10){
                    munculPopup();
                }else {
                    updateQuestion(list.size());
                }
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailQuisActivity.this,
                        "Opsss.... Terjadi kesalahan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void munculPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Mohon maaf soal ini belum bisa dipilih ?")
                .setCancelable(false)
                .setPositiveButton("Keluar",
                        (dialog, id) -> {
                    dialog.dismiss();
                    finish();
                });
        AlertDialog alert = builder.create();
        alert.show();

        //for positive side button
        alert.getButton(alert.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    private void updateQuestion(int num) {
        if (tvScore.getText().toString().equalsIgnoreCase("Soal : 10 / 10")){
            TextView nilai = findViewById(R.id.nilai);
            nilai.setText("Nilai : "+hasil_jawaban);
            SoalBerakhir();
        }else {
            int minimum = 1;
            int range = num-minimum+1;
            Random random = new Random();
            int randomAngka = random.nextInt(range)+minimum;
            tvQuestion.setText(list.get(randomAngka-1).getSoal());
            btnAnswerA.setText(list.get(randomAngka-1).getPilihan1());
            btnAnswerB.setText(list.get(randomAngka-1).getPilihan2());
            btnAnswerC.setText(list.get(randomAngka-1).getPilihan3());
            btnAnswerD.setText(list.get(randomAngka-1).getPilihan4());
            jawab = list.get(randomAngka-1).getJawaban();
            lagu = list.get(randomAngka-1).getLagu();

            Log.e("int", "randomAngka " + (randomAngka-1));
            Log.e("jawab", "jawab " + jawab);
            stopAudio();
        }
//        }
//        initAudio();
    }


    private ProgressDialog progressDialog;
    private void insial() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Menyiapkan Data");
        progressDialog.show();

        mediaplayer = new MediaPlayer();

        tvScore = findViewById(R.id.tvScore);
        btnStart = findViewById(R.id.btnStart);
        btnStop  = findViewById(R.id.btnStop);
        playerSeekBar = findViewById(R.id.playerSeekBar);
        tvQuestion = findViewById(R.id.tvQuestion);

        btnAnswerA = findViewById(R.id.btnAnswerA);
        btnAnswerB = findViewById(R.id.btnAnswerB);
        btnAnswerC = findViewById(R.id.btnAnswerC);
        btnAnswerD = findViewById(R.id.btnAnswerD);
    }

    public void SoalBerakhir() {
        stopAudio();
        final Dialog dialog = new Dialog(DetailQuisActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_info);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView tvInfo = dialog.findViewById(R.id.tvInfo);
        Button btnExit = dialog.findViewById(R.id.btnExit);
        Button btnNewGame = dialog.findViewById(R.id.btnNewGame);
        ImageView ivIndicator = dialog.findViewById(R.id.ivIndicator);

        if (hasil_jawaban>7) {
            tvInfo.setText("Selesai !, Kamu mendapat " + hasil_jawaban+".");
            ivIndicator.setImageResource(R.drawable.ic_smile);
        } if (hasil_jawaban>=4 && hasil_jawaban<=7){
            ivIndicator.setImageResource(R.drawable.ic_confused);
            tvInfo.setText("Permainan selesai !, Poin kamu " + hasil_jawaban+".");
        }else{
            ivIndicator.setImageResource(R.drawable.ic_sad);
            tvInfo.setText("Yaaaah... Poin kamu " + hasil_jawaban+".");
        }
        btnExit.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });
        btnNewGame.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(DetailQuisActivity.this, StartGameActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        dialog.show();

    }

    private MediaPlayer mediaplayer;

    private void stopAudio() {
//        mediaplayer.stop();
        if (mediaplayer.isPlaying()) {
            mediaplayer.stop();
        }
        try {
//            mediaplayer.prepare();
//            mediaplayer.seekTo(0);
            if (mediaplayer.isPlaying()) {
                mediaplayer.prepareAsync();
                mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaplayer.seekTo(0);
                    }
                });
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        btnStart.setVisibility(View.VISIBLE);
        btnStop.setVisibility(View.GONE);
    }

    private void playAudio(String filename) {
        Log.e("Filename", filename);
        mediaplayer = new MediaPlayer();
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            mediaplayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());
//        } else {
//            mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        }
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaplayer.setDataSource(filename);
            mediaplayer.prepareAsync();
            mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    btnStart.setVisibility(View.GONE);
                    btnStop.setVisibility(View.VISIBLE);
                    mediaplayer.start();
                }
            });
            mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    btnStart.setVisibility(View.VISIBLE);
                    btnStop.setVisibility(View.GONE);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(DetailQuisActivity.this,
                    "Maaf tidak dapat memutar lagu", Toast.LENGTH_SHORT).show();
        }
    }
}