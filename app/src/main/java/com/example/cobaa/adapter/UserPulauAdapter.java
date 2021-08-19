package com.example.cobaa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cobaa.R;
import com.example.cobaa.activities.user.DetailQuisActivity;
import com.example.cobaa.models.PulauModel;

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
        holder.banner.setOnClickListener(view -> {
            Intent intent = new Intent(context,  DetailQuisActivity.class);
            intent.putExtra("jenis_soal", "map");
            intent.putExtra("map", list.get(position).getName_pulau().toLowerCase());
            intent.putExtra("title", list.get(position).getName_pulau().toLowerCase());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

}