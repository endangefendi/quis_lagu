package com.example.cobaa.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cobaa.R;
import com.example.cobaa.activities.admin.AddLaguActivity;
import com.example.cobaa.activities.admin.MasterBannerActivity;
import com.example.cobaa.activities.admin.MasterDataPulauActivity;
import com.example.cobaa.activities.admin.MasterDataSoalActivity;
import com.example.cobaa.activities.admin.MasterLaguActivity;
import com.example.cobaa.models.MenuModel;

import java.util.ArrayList;

public class MenuAdminAdapter extends  RecyclerView.Adapter<MenuAdminAdapter.ViewHolder>{
    private final Context context;
    private final ArrayList<MenuModel> list;

    public MenuAdminAdapter(Context context, ArrayList<MenuModel> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public Button btn_menu;

        public ViewHolder(View v) {
            super(v);
            btn_menu = v.findViewById(R.id.btn_menu);
         }
    }

    @NonNull
    @Override
    public MenuAdminAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuAdminAdapter.ViewHolder holder, final int position) {
        holder.btn_menu.setText(list.get(position).getName_menu());

        holder.btn_menu.setOnClickListener(view -> {
            if (list.get(position).getName_menu().toLowerCase().contains("data pulau")){
                Bundle bundle= new Bundle();
                Intent intent = new Intent(holder.itemView.getContext(), MasterDataPulauActivity.class);
                bundle.putString("key_menu", list.get(position).getName_menu());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }else if (list.get(position).getName_menu().toLowerCase().contains("banner")){
                Bundle bundle= new Bundle();
                Intent intent = new Intent(holder.itemView.getContext(), MasterBannerActivity.class);
                bundle.putString("key_menu", list.get(position).getName_menu());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }else if (list.get(position).getName_menu().toLowerCase().contains("lagu")){
                Bundle bundle= new Bundle();
                Intent intent = new Intent(holder.itemView.getContext(), MasterLaguActivity.class);
                bundle.putString("key_menu", list.get(position).getName_menu());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }else {
                Bundle bundle= new Bundle();
                Intent intent = new Intent(holder.itemView.getContext(), MasterDataSoalActivity.class);
                bundle.putString("key_menu", list.get(position).getName_menu());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
