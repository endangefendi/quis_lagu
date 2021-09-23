package com.example.cobaa.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cobaa.R;
import com.example.cobaa.models.LaguModel;
import com.example.cobaa.models.PulauModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterLagu extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<LaguModel> movieItems;
    private ArrayList<LaguModel> listlokasiasli;


    public AdapterLagu(Context context, List<LaguModel> movieItems) {
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

        LaguModel m = movieItems.get(position);

        tv_daerah.setText(m.getNama());

        return convertView;
    }

    public void setList(List<LaguModel> movieItems){
        this.listlokasiasli.addAll(movieItems);
    }
}