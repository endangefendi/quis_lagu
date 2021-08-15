package com.example.cobaa.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import java.util.List;

public class DaerahAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<PulauModel> movieItems;
    private ArrayList<PulauModel> listlokasiasli;


    public DaerahAdapter( Context context, List<PulauModel> movieItems) {
        this.context = context;
        this.movieItems = movieItems;

        listlokasiasli = new ArrayList<>();
        listlokasiasli.addAll(movieItems);
    }
    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            assert inflater != null;
            convertView = inflater.inflate(R.layout.item_daerah, null);
        }

        TextView tv_daerah = convertView.findViewById(R.id.tv_daerah);

        PulauModel m = movieItems.get(position);

        tv_daerah.setText(m.getName_pulau());

        return convertView;
    }

    public void setList(List<PulauModel> movieItems){
        this.listlokasiasli.addAll(movieItems);
    }
}