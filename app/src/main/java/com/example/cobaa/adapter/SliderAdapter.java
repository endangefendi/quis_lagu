package com.example.cobaa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cobaa.R;
import com.example.cobaa.models.BannerModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolderSlider> {

    private Context context;
    ArrayList<BannerModel> list;

    public SliderAdapter(Context context, ArrayList<BannerModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) return list.size();
        return 0;
    }

    @Override
    public ViewHolderSlider onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_slide, null);
        return new ViewHolderSlider(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolderSlider viewHolder, int position) {
        viewHolder.textViewDescription.setText(list.get(position).getName_banner());
        Glide.with(viewHolder.itemView)
                .load(list.get(position).getBanner())
                .placeholder(R.drawable.ic_image)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .thumbnail(0.5f)
                .into(viewHolder.imageViewBackground);
    }

    class ViewHolderSlider extends SliderViewAdapter.ViewHolder{
        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public ViewHolderSlider(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
