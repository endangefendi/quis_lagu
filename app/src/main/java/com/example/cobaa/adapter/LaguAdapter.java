package com.example.cobaa.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaa.R;
import com.example.cobaa.models.LaguModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

public class LaguAdapter extends  RecyclerView.Adapter<LaguAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<LaguModel> list;
    private final MediaPlayer mp;
    private boolean isPlaying;
    private ProgressDialog progressDialog;

    public LaguAdapter(Context context, ArrayList<LaguModel> list, MediaPlayer mp) {
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
        private final Button btn_hapus;
        private final Button btn_edit;

        public ViewHolder(View v) {
            super(v);
            nomor = v.findViewById(R.id.nomor);
            tv_question = v.findViewById(R.id.tv_lagu);
            btnStart = v.findViewById(R.id.btnStart);
            btnStop = v.findViewById(R.id.btnStop);
            btn_hapus = v.findViewById(R.id.btn_hapus);
            btn_edit = v.findViewById(R.id.btn_edit);
        }
    }

    @NonNull
    @Override
    public LaguAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_master_lagu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LaguAdapter.ViewHolder holder, final int position) {
        holder.tv_question.setText(list.get(position).getNama());
        holder.nomor.setText(String.valueOf(position + 1));
        holder.btnStart.setOnClickListener(view -> {
            if (list.get(position).getUrl().contains("https://firebasestorage.googleapis.com")) {
                if (!isPlaying) {
                    play_audio(list.get(position).getUrl(), holder);
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

        holder.btn_hapus.setOnClickListener(view -> Hapus(holder));

        holder.btn_edit.setOnClickListener(view -> Edit(holder));

    }

    private void Edit(ViewHolder holder) {
        final String id = list.get(holder.getAdapterPosition()).getId();
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_edit);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        EditText txt_nama = dialog.findViewById(R.id.txt_nama);
        Button btnExit = dialog.findViewById(R.id.btnExit);
        Button btnSimpan = dialog.findViewById(R.id.btnSimpan);
        txt_nama.setText(list.get(holder.getAdapterPosition()).getNama());
        btnExit.setOnClickListener(v ->
            dialog.dismiss()
        );

        btnSimpan.setOnClickListener(v -> {
            if (txt_nama.getText().toString().trim().equalsIgnoreCase("")){
                txt_nama.setError("Nama Banner Tidak boleh kosong");
            }else{
                saving(id, txt_nama.getText().toString().trim());
                dialog.dismiss();
            }
        });

        dialog.show();


    }


    private void saving(String id, String name_banner) {
        if (id != null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Menyimpan Data");
            progressDialog.show();
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Lagu");
            try {
                ref.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ref.child(id).child("nama").setValue(name_banner);

                        progressDialog.dismiss();
                        Toast.makeText(context,
                                "Edit Data successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "Pastikan semua data sudah benar",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void Hapus(LaguAdapter.ViewHolder holder) {
        final String id = list.get(holder.getAdapterPosition()).getId();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Yakin ingin menghapus Lagu ini ?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.cancel();
                    ProgressDialog  progressDialog = ProgressDialog.show(context, "Please wait...",
                            "Processing...", true);
                    progressDialog.show();
                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Lagu");
                    try {
                        ref.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                                    appleSnapshot.getRef().removeValue();
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Data Soal berhasil dihapus", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.e("SoalAdapter", "onCancelled", databaseError.toException());
                            }

                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .setNegativeButton("No", (dialog, which) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
        //for positive side button
        alert.getButton(alert.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.colorPrimary));
        //for negative side button
        alert.getButton(alert.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.colorPrimary));
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