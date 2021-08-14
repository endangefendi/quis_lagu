package com.example.cobaa.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaa.R;
import com.example.cobaa.models.SoalModel;

import java.io.IOException;
import java.util.ArrayList;

public class SoalAdapter extends  RecyclerView.Adapter<SoalAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<SoalModel> list;

    private final MediaPlayer mp;
    private boolean isPlaying;

    public SoalAdapter(Context context, ArrayList<SoalModel> list, MediaPlayer mp) {
        this.context = context;
        this.list = list;
        this.mp = mp;
        isPlaying = false;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_question;
        private final TextView nomor;
        private final ImageView btnStart;
        private final ImageView btnStop;

        public ViewHolder(View v) {
            super(v);
            nomor = v.findViewById(R.id.nomor);
            tv_question = v.findViewById(R.id.tv_question);
            btnStart = v.findViewById(R.id.btnStart);
            btnStop = v.findViewById(R.id.btnStop);
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