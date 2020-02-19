package com.sanved.parkdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sanved on 28-06-2018.
 */

public class ParkInfo extends AppCompatActivity {

    String title, desc, imgid, json;
    int open, close, temp;
    TextView tvtitle, tvdesc, openClose;
    ImageView ivPoster;
    PanoramaImageView ivCover;
    RecyclerView rv;
    Button call;

    RVAdaptFeature adapt;

    ParkChiMahiti p1;

    ArrayList list;
    LatLng latlongg;
    SupportMapFragment mapFragment;

    private GyroscopeObserver gyroscopeObserver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.park_info);

        // Getting JSON from previous activity
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            json = extras.getString("json");
        }else{
            json = (String) savedInstanceState.getSerializable("json");
        }

        // Extracting data from JSON
        Gson gson = new Gson();
        p1 = gson.fromJson(json, ParkChiMahiti.class);

        title = p1.getTitle();
        desc = p1.getDesc();
        imgid = p1.getImgid();
        open = p1.getOpen();
        close = p1.getClose();

        latlongg = new LatLng(p1.getLatt(), p1.getLongg());

        initVals();

        initFeatureList();

    }

    public void initVals(){

        gyroscopeObserver = new GyroscopeObserver();
        // Set the maximum radian the device should rotate to show image's bounds.
        // It should be set between 0 and π/2.
        gyroscopeObserver.setMaxRotateRadian(Math.PI/15);

        tvtitle = findViewById(R.id.tvTitle);
        tvdesc = findViewById(R.id.tvParkDesc);

        ivPoster = findViewById(R.id.ivParkCover);
        ivCover = findViewById(R.id.ivCover);
        openClose = findViewById(R.id.tvOpenClose);

        call = findViewById(R.id.bCall);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + p1.getPhno()));
                startActivity(in);
            }
        });

        tvtitle.setText(title);
        tvdesc.setText(desc);

        setOpenCloseTime();

        ivCover.setEnablePanoramaMode(true);
        ivCover.setGyroscopeObserver(gyroscopeObserver);
        ivCover.setEnableScrollbar(false);

        int drawableResourceId = getResources().getIdentifier(imgid, "drawable", getPackageName());
        Picasso.get().load(drawableResourceId).into(ivCover);

        list = new ArrayList();

        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                googleMap.addMarker(new MarkerOptions()
                        .position(latlongg)
                        .title(title)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlongg, 14));
            }
        });

    }

    public void initFeatureList(){

        if(p1.isBicycle()) list.add(new InfoItem("bicycle", "Bicycle Track"));
        if(p1.isCafe()) list.add(new InfoItem("cafe", "Cafe"));
        if(p1.isFair()) list.add(new InfoItem("fair", "Fair"));
        if(p1.isParking()) list.add(new InfoItem("parking", "Parking Available"));
        if(p1.isPhoto()) list.add(new InfoItem("photo", "Photography Allowed"));
        if(p1.isTicket()) list.add(new InfoItem("ticket", "Entry Ticket"));
        if(p1.isWifi()) list.add(new InfoItem("wifi", "WiFi Available"));

        Context con = this.getApplication();
        adapt = new RVAdaptFeature(list, con);

        rv.setAdapter(adapt);

    }

    public void setOpenCloseTime(){
        StringBuilder stringBuilder = new StringBuilder();
        // Set Open Time
        if(open == 12){
            stringBuilder.append("" + open + " pm");
        }
        else if(open > 12) {
            temp = open - 12;
            stringBuilder.append("" + temp + " pm");
        }else{
            stringBuilder.append("" + open + " am");
        }

        // Set Close Time
        if(close == 12){
            stringBuilder.append(" - " + close + " pm");
        }
        else if(close > 12) {
            temp = close - 12;
            stringBuilder.append(" - " + temp + " pm");
        }else{
            stringBuilder.append(" - " + close + " am");
        }

        String timing = stringBuilder.toString();

        openClose.setText(timing);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register GyroscopeObserver.
        // OnResume inits at startup
        gyroscopeObserver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister GyroscopeObserver.
        // Unregister to let other apps use it
        gyroscopeObserver.unregister();
    }

}
