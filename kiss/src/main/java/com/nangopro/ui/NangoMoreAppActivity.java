package com.nangopro.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nangopro.AAAAConfigApplicaion;
import com.nangopro.R;
import com.nangopro.adapter.NangoMoreAppAdapter;
import com.nangopro.obj.NangoOConfig;

import java.util.List;

public class NangoMoreAppActivity extends AppCompatActivity {

    RecyclerView ryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_app);
        ryData = findViewById(R.id.ryData);
        List<NangoOConfig.App> apps = AAAAConfigApplicaion.getCOnfig().apps;
        Log.e("APPS",apps.toString());
        NangoMoreAppAdapter nangoMoreAppAdapter = new NangoMoreAppAdapter(this, apps);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        ryData.setLayoutManager(linearLayoutManager);
        ryData.setAdapter(nangoMoreAppAdapter);
    }

    public void cickClose(View view) {
        finish();

    }
}
