package com.example.nicknamegeneradorpro.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.nicknamegeneradorpro.R;
import com.example.nicknamegeneradorpro.ui.InterstitialAdmobActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;

public class DialogCustom extends Dialog {

    public InterstitialAd interstitialAd;
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
    TextView content;
    LinearLayout close;
    Context context;

    public DialogCustom(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_custom);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        content = findViewById(R.id.content1);
        close   = findViewById(R.id.layout_close);

        setCancelable(false);
        init();
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                startGame();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdsUtils.showInterstitial(40, new AdsUtils.LoadAds() {
                    @Override
                    public void onOk() {
                        dismiss();
                    }
                });
            }
        });
        startGame();
    }
    public void init(){
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(AD_UNIT_ID);
    }
    public void  startGame(){
        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }
    }
    public void setContent(String s){
        content.setText(s);
    }
}
