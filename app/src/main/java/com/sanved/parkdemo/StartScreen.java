package com.sanved.parkdemo;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.util.ArrayList;

public class StartScreen extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv;
    TextView tool_title;
    RelativeLayout rl1;
    LinearLayout ll1;
    View contentHamburger;
    View guillotineMenu;

    // Menu
    LinearLayout profile, feed, touch, settings;

    RVAdapt adapt;
    ArrayList<ParkChiMahiti> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_screen);

        initVals();

    }

    public void initVals() {

        FirebaseMessaging.getInstance().subscribeToTopic("testing");

        rv = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        toolbar = findViewById(R.id.toolbar);
        tool_title = findViewById(R.id.toolbar_title);
        tool_title.setText("Broom County");
        Typeface newFont = Typeface.createFromAsset(getAssets(), "vegan.ttf");
        tool_title.setTypeface(newFont);

        rl1 = findViewById(R.id.rl1);
        contentHamburger = findViewById(R.id.content_hamburger);

        list = new ArrayList<ParkChiMahiti>();
        hardCodeTest();
        Context con = this.getApplication();
        adapt = new RVAdapt(list, con);

        rv.setAdapter(adapt);

        guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        rl1.addView(guillotineMenu);

        initMenuItems();

        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setActionBarViewForAnimation(toolbar)
                .setStartDelay(100)
                .setClosedOnStart(true)
                .build();

    }

    public void hardCodeTest() {

        String parkDesc;

        parkDesc = "The best things to do in Central Park aren’t just for tourists! There are attractions for everyone in NYC’s iconic park.";
        ParkChiMahiti p1 = new ParkChiMahiti("Central Park", parkDesc, "cent", "central", 9, 18, 40.7828647, -73.9653551, "12345");
        p1.setBicycle(true);
        p1.setCafe(true);
        p1.setPhoto(true);
        p1.setWifi(true);
        list.add(p1);

        parkDesc = "Built by the New York State Department of Transportation and leased, improved and maintained by Broome County Parks Department, Otsiningo provides a refreshing interlude close to the urban core";
        ParkChiMahiti p2 = new ParkChiMahiti("Otsiningo Park", parkDesc, "otsi", "otsiningo", 8, 20, 42.124324, -75.9028, "12345");
        p2.setBicycle(true);
        p2.setPhoto(true);
        p2.setTicket(true);
        list.add(p2);

    }

    public void initMenuItems(){

        ll1 = guillotineMenu.findViewById(R.id.ll1);
        profile = guillotineMenu.findViewById(R.id.profile_group);
        feed = guillotineMenu.findViewById(R.id.feed_group);
        touch = guillotineMenu.findViewById(R.id.activity_group);
        settings = guillotineMenu.findViewById(R.id.settings_group);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartScreen.this, "Profile", Toast.LENGTH_SHORT).show();
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartScreen.this, "Feed", Toast.LENGTH_SHORT).show();
            }
        });

        touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartScreen.this, "Get in Touch", Toast.LENGTH_SHORT).show();
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartScreen.this, "Settings", Toast.LENGTH_SHORT).show();
            }
        });

        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Guillotine","Click absorbed");
            }
        });

    }
}
