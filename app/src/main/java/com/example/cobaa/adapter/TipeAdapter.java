package com.example.cobaa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cobaa.R;
import com.example.cobaa.models.PulauModel;
import com.example.cobaa.models.SoalModel;

import java.util.ArrayList;
import java.util.List;

public class TipeAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<String> movieItems;
    private ArrayList<String> listlokasiasli;


    public TipeAdapter(Context context, List<String> movieItems) {
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

        tv_daerah.setText(movieItems.get(position));

        return convertView;
    }

    public void setList(List<String> movieItems){
        this.listlokasiasli.addAll(movieItems);
    }
}