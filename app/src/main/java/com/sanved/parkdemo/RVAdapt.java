package com.sanved.parkdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sanved on 26-06-2018.
 */

public class RVAdapt extends RecyclerView.Adapter<RVAdapt.DataHolder> {

    ArrayList<ParkChiMahiti> list;
    static ArrayList<ParkChiMahiti> list2;
    static Context context;
    static SharedPreferences prefs;
    static SharedPreferences.Editor ed;

    RVAdapt(ArrayList<ParkChiMahiti> list, Context context){
        this.list = list;
        list2 = list;
        this.context = context;
        prefs = context.getSharedPreferences("parknoti", Context.MODE_PRIVATE);
        ed = prefs.edit();
    }

    public static class DataHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc;
        ImageView ivPoster;
        CoordinatorLayout cl1;
        ImageButton noti;

        DataHolder(final View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvDesc =  v.findViewById(R.id.tvParkDesc);
            ivPoster = v.findViewById(R.id.ivParkCover);
            cl1 = v.findViewById(R.id.cl1);
            noti = v.findViewById(R.id.ibNoti);

            cl1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, ParkInfo.class);

                    Gson gson = new Gson();
                    String json = gson.toJson(list2.get(getAdapterPosition()));

                    i.putExtra("json", json);

                    context.startActivity(i);

                }
            });

            noti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean isNotificationOn =  prefs.getBoolean(list2.get(getAdapterPosition()).getImgid(),false);

                    if(isNotificationOn){
                        // if ON, this will turn it off
                        FirebaseMessaging.getInstance().unsubscribeFromTopic(list2.get(getAdapterPosition()).getFireID());
                        ed.putBoolean(list2.get(getAdapterPosition()).getImgid(),false).commit();
                        noti.setBackground(ContextCompat.getDrawable(context, R.drawable.fade_white));
                        noti.setImageResource(R.drawable.baseline_notifications_black_24);
                    }else{
                        // if OFF, this will turn it on
                        FirebaseMessaging.getInstance().subscribeToTopic(list2.get(getAdapterPosition()).getFireID());
                        ed.putBoolean(list2.get(getAdapterPosition()).getImgid(),true).commit();
                        noti.setBackground(ContextCompat.getDrawable(context, R.drawable.fade_yellow));
                        noti.setImageResource(R.drawable.baseline_notifications_active_black_24);
                    }

                }
            });

        }

    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        DataHolder dh = new DataHolder(v);
        return dh;
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.tvDesc.setText(""+list.get(position).getDesc());
        int drawableResourceId = context.getResources().getIdentifier(list.get(position).getImgid(), "drawable", context.getPackageName());
        Picasso.get().load(drawableResourceId).into(holder.ivPoster);

        // check if notification is allowed
        boolean isNotificationOn =  prefs.getBoolean(list2.get(position).getImgid(),false);
        if(isNotificationOn) {
            // If ON, it will set the icons while loading
            ed.putBoolean(list2.get(position).getImgid(),true).commit();
            holder.noti.setBackground(ContextCompat.getDrawable(context, R.drawable.fade_yellow));
            holder.noti.setImageResource(R.drawable.baseline_notifications_active_black_24);
        }

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
