package com.example.cobaa.adapter;

import android.content.Context;
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
import com.example.cobaa.models.BannerModel;
import java.util.ArrayList;

public class BannerAdapter extends  RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<BannerModel> list;


    public BannerAdapter(Context context, ArrayList<BannerModel> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView banner;
        private final TextView id_banner;
        private final TextView name_banner;

        public ViewHolder(View v) {
            super(v);
            banner = v.findViewById(R.id.banner);
            id_banner = v.findViewById(R.id.id_banner);
            name_banner = v.findViewById(R.id.name_banner);
        }
    }

    @NonNull
    @Override
    public BannerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_master_banner, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final BannerAdapter.ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getBanner())
                .placeholder(R.drawable.ic_image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .thumbnail(0.5f)
                .into(holder.banner);

        holder.id_banner.setText(list.get(position).getId());
        holder.name_banner.setText(list.get(position).getName_banner());

//        holder.btn_menu.setOnClickListener(view -> {
//            Bundle bundle= new Bundle();
//            Intent intent = new Intent(holder.itemView.getContext(), MasterDataSoalActivity.class);
//            bundle.putString("key_menu", list.get(position).getJawaban());
//            intent.putExtras(bundle);
//            context.startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }
}