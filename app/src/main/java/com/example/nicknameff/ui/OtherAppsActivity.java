package com.example.nicknameff.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nicknameff.R;
import com.example.nicknameff.adapter.MoreAppsAdapter;
import com.example.nicknameff.model.Config;

import java.util.ArrayList;

public class OtherAppsActivity extends AppCompatActivity implements MoreAppsAdapter.OnAd {
    RecyclerView recyclerView;
    ArrayList<Config.App> ListApp;
    ImageView back, notice;
    TextView textad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_other_apps);
        Init();



        MoreAppsAdapter moreAppsAdapter = new MoreAppsAdapter(ListApp, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(moreAppsAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        notice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TimeUtils.setTimeinterval(1000, new TimeUtils.CallBack() {
//                    @Override
//                    public void callBack() {
//                        textad.setVisibility(View.VISIBLE);
//                    }
//
//                    @Override
//                    public void callBack(int i) {
//                        textad.setVisibility(View.INVISIBLE);
//                    }
//                });
//            }
//        });
    }

    public void Init() {
        recyclerView = findViewById(R.id.recyclerviewotherapps);
        back = findViewById(R.id.back);
        notice = findViewById(R.id.baseline1);
        textad = findViewById(R.id.textad);
        ListApp = new ArrayList<>();
        Config o = Config.getOConfig(this);
        ListApp = (ArrayList<Config.App>) o.apps;
        //ListApp =
    }

    @Override
    public void OnAdListener(int position) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ListApp.get(position).link)));
    }

}