package com.example.cobaa.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cobaa.R;
import com.example.cobaa.models.PulauModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserPulauAdapter extends  RecyclerView.Adapter<UserPulauAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<PulauModel> list;

    public UserPulauAdapter(Context context, ArrayList<PulauModel> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView banner;
        private final TextView name_banner;


        public ViewHolder(View v) {
            super(v);
            banner = v.findViewById(R.id.iv_auto_image_slider);
            name_banner = v.findViewById(R.id.name_banner);
        }
    }

    @NonNull
    @Override
    public UserPulauAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pulau_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserPulauAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getIcon())
                .placeholder(R.drawable.ic_image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .thumbnail(0.5f)
                .into(holder.banner);

        holder.name_banner.setText(list.get(position).getName_pulau());

    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

}