package com.example.cobaa.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cobaa.R;
import com.example.cobaa.models.BannerModel;
import com.example.cobaa.models.PulauModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PulauAdapter extends  RecyclerView.Adapter<PulauAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<PulauModel> list;
    private ProgressDialog progressDialog;

    public PulauAdapter(Context context, ArrayList<PulauModel> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView banner;
        private final TextView id_banner;
        private final TextView name_banner;

        private final Button btn_hapus;

        public ViewHolder(View v) {
            super(v);
            btn_hapus = v.findViewById(R.id.btn_hapus);
            banner = v.findViewById(R.id.banner);
            id_banner = v.findViewById(R.id.id_banner);
            name_banner = v.findViewById(R.id.name_banner);
        }
    }

    @NonNull
    @Override
    public PulauAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_master_banner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PulauAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getIcon())
                .placeholder(R.drawable.ic_image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .thumbnail(0.5f)
                .into(holder.banner);

        holder.id_banner.setText(list.get(position).getId());
        holder.name_banner.setText(list.get(position).getName_pulau());

        holder.btn_hapus.setOnClickListener(v -> konfirDelete(holder));

    }


    private void konfirDelete(ViewHolder holder) {
        final String id = list.get(holder.getAdapterPosition()).getId();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Yakin ingin menghapus banner ?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.cancel();
                    progressDialog = ProgressDialog.show(context, "Please wait...",
                            "Processing...", true);
                    progressDialog.show();
                    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("master pulau");
                    try {
                        ref.orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                                    appleSnapshot.getRef().removeValue();
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Data Banner berhasil dihapus", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.e("BannerAdapter", "onCancelled", databaseError.toException());
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

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

}