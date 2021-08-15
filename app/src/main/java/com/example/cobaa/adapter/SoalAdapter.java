package com.example.cobaa.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaa.R;
import com.example.cobaa.activities.EditSoalMapActivity;
import com.example.cobaa.activities.EditSoalRandomActivity;
import com.example.cobaa.models.SoalModel;

import java.io.IOException;
import java.util.ArrayList;

public class SoalAdapter extends  RecyclerView.Adapter<SoalAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<SoalModel> list;

    private final MediaPlayer mp;
    private final String key_menu;
    private boolean isPlaying;

    public SoalAdapter(Context context, ArrayList<SoalModel> list, MediaPlayer mp, String key_menu) {
        this.context = context;
        this.list = list;
        this.mp = mp;
        isPlaying = false;
        this.key_menu = key_menu;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_question;
        private final TextView nomor;
        private final ImageView btnStart;
        private final ImageView btnStop;
        private final RelativeLayout frame_soal;

        public ViewHolder(View v) {
            super(v);
            nomor = v.findViewById(R.id.nomor);
            tv_question = v.findViewById(R.id.tv_question);
            btnStart = v.findViewById(R.id.btnStart);
            btnStop = v.findViewById(R.id.btnStop);
            frame_soal = v.findViewById(R.id.frame_soal);
        }
    }

    @NonNull
    @Override
    public SoalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_master_soal, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final SoalAdapter.ViewHolder holder, final int position) {
        holder.tv_question.setText(list.get(position).getSoal());
        holder.nomor.setText(String.valueOf(position + 1));
        holder.btnStart.setOnClickListener(view -> {
            if (list.get(position).getLagu().contains("https://firebasestorage.googleapis.com")) {
                if (!isPlaying) {
                    play_audio(list.get(position).getLagu(), holder);
                    isPlaying = true;
                    holder.btnStart.setVisibility(View.GONE);
                    holder.btnStop.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(context, "Opsss.... Lagu tidak dapat diputar", Toast.LENGTH_LONG).show();
            }
        });

        holder.btnStop.setOnClickListener(view -> {
            if (isPlaying) {
                mp.stop();
                mp.reset();
                isPlaying = false;
                holder.btnStart.setVisibility(View.VISIBLE);
                holder.btnStop.setVisibility(View.GONE);
            }
        });

        holder.frame_soal.setOnClickListener(view -> {
            Bundle bundle= new Bundle();
            bundle.putString("id", list.get(position).getId());
            bundle.putString("jawaban", list.get(position).getJawaban());
            bundle.putString("jenis_soal", list.get(position).getJenis_soal());
            bundle.putString("lagu", list.get(position).getLagu());
            bundle.putString("map", list.get(position).getMap());
            bundle.putString("pilihan1", list.get(position).getPilihan1());
            bundle.putString("pilihan2", list.get(position).getPilihan2());
            bundle.putString("pilihan3", list.get(position).getPilihan3());
            bundle.putString("pilihan4", list.get(position).getPilihan4());
            bundle.putString("soal", list.get(position).getSoal());

            if (!key_menu.isEmpty()) {
                if (key_menu.toLowerCase().contains("soal random")) {
                    if (isPlaying) {
                        mp.stop();
                        mp.reset();
                        isPlaying = false;
                        holder.btnStart.setVisibility(View.VISIBLE);
                        holder.btnStop.setVisibility(View.GONE);
                        Intent intent = new Intent(holder.itemView.getContext(), EditSoalRandomActivity.class);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(holder.itemView.getContext(), EditSoalRandomActivity.class);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                } else {
                    if (isPlaying) {
                        mp.stop();
                        mp.reset();
                        isPlaying = false;
                        holder.btnStart.setVisibility(View.VISIBLE);
                        holder.btnStop.setVisibility(View.GONE);
                        Intent intent = new Intent(holder.itemView.getContext(), EditSoalMapActivity.class);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(holder.itemView.getContext(), EditSoalMapActivity.class);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                }
            }
        });

    }

    private void play_audio(final String audio, final ViewHolder holder) {
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
            holder.btnStart.setVisibility(View.VISIBLE);
            holder.btnStop.setVisibility(View.GONE);
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }
}