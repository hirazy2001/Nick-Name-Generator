package com.example.nicknameff.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.nicknameff.Constant.Constants;
import com.example.nicknameff.R;
import com.example.nicknameff.model.Config;
import com.example.nicknameff.utils.SharedPreferencesUtils;
import com.example.nicknameff.utils.TimeUtils;
import com.google.gson.Gson;
import com.jgabrielfreitas.core.BlurImageView;

public class F_Activity extends AppCompatActivity implements TimeUtils.CallBack {
    ImageView img_close;
    BlurImageView imgBg;
    RelativeLayout rlbanner;
    ProgressBar progressBar;
    private final int SPLASH_DELAY = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        progressBar = findViewById(R.id.progressBar);
        imgBg = findViewById(R.id.bg);
        //BlurImage.with(getApplicationContext()).load(R.mipmap.ic_banner).intensity(20).Async(true).into(imgBg);
        imgBg.setBlur(20);
        rlbanner = findViewById(R.id.rlbanner);
        TimeUtils.setTimeinterval(1000, this);
        img_close = findViewById(R.id.btn_close);
        progressBar.setMax(100);

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FaceBookAdsUtils ads = new FaceBookAdsUtils();
//                ads.showInterstitial(FirstActivity.this,Constant.SETTING, 100, new FaceBookAdsUtils.LoadAds() {
//                    @Override
//                    public void onOk() {
//                        Intent intent = new Intent(FirstActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
            }
        });
        Log.e("AndroidNetworking", "onCreate: hehehehehe");
        AndroidNetworking.get(Constants.URL_CONFIG)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(Config.class, new ParsedRequestListener<Config>() {
                    @Override
                    public void onResponse(Config config) {
                        Log.e("AndroidNetworking", config.toString());
                        String json = config.toString();
                        Log.e("listCoppy", json);
                        SharedPreferencesUtils.saveData(F_Activity.this, Constants.LIST_CONFIG,
                                new Gson().toJson(config));
                        //new FaceBookAdsUtils().showBanner(Constant.SETTING,AdSize.RECTANGLE_HEIGHT_250, rlbanner);
                        if(!config.showAds){
                            Intent intent = new Intent(F_Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Intent intent = new Intent(F_Activity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });


    }

    @Override
    public void callBack() {
        Log.e("TAG", "callBack: ");
        runOnUiThread(() -> {
            progressBar.setVisibility(View.INVISIBLE);
            img_close.setVisibility(View.VISIBLE);
        });

    }

    @Override
    public void callBack(int i) {
        progressBar.setProgress(i);
    }
}