package com.sanved.parkdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sanved on 12-07-2018.
 */

public class RVAdaptFeature extends RecyclerView.Adapter<RVAdaptFeature.DataHolder> {

    ArrayList<InfoItem> list;
    static ArrayList<InfoItem> list2;
    static Context context;
    static SharedPreferences prefs;
    static SharedPreferences.Editor ed;

    RVAdaptFeature(ArrayList<InfoItem> list, Context context){
        this.list = list;
        list2 = list;
        this.context = context;
        prefs = context.getSharedPreferences("parknoti", Context.MODE_PRIVATE);
        ed = prefs.edit();
    }

    public static class DataHolder extends RecyclerView.ViewHolder {

        TextView tvFeature;
        ImageView ivBlock;

        DataHolder(final View v) {
            super(v);
            tvFeature = v.findViewById(R.id.tvFeature);
            ivBlock =  v.findViewById(R.id.ivBlock);
        }

    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_info, parent, false);
        DataHolder dh = new DataHolder(v);
        return dh;
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        holder.tvFeature.setText(list.get(position).getDesc());
        int drawableResourceId = context.getResources().getIdentifier(list.get(position).getImgid(), "drawable", context.getPackageName());
        Picasso.get().load(drawableResourceId).into(holder.ivBlock);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
