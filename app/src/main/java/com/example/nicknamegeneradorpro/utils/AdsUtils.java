package com.example.nicknamegeneradorpro.utils;

import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nicknamegeneradorpro.BuildConfig;
import com.example.nicknamegeneradorpro.Constant.Constant;
import com.example.nicknamegeneradorpro.R;
import com.example.nicknamegeneradorpro.application.MyApplication;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.service.InShotSplashAdRequest;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;

import java.util.Random;

public class AdsUtils {
    public interface LoadAds {
        void onOk();
    }

    static InterstitialAd interstitialAd;
    static LoadAds loadAds;

    public static void splashBannerAd() {
        InShotSplashAdRequest adRequest = new InShotSplashAdRequest();
        if (BuildConfig.DEBUG) adRequest.setBannerId("ca-app-pub-3940256099942544/6300978111");
        else adRequest.setBannerId(Constant.ADS.BANNER_SETTING);
        if (BuildConfig.DEBUG) adRequest.setInterId("ca-app-pub-3940256099942544/1033173712");
        else adRequest.setInterId(Constant.ADS.INTER_SETIING);
        adRequest.setResLogo(R.mipmap.ic_launcher);
        adRequest.setResBanner(R.mipmap.ic_banner1);
        adRequest.setAlwayShowAd(true);
        AudienceNetworkAds.loadSplashAd(MyApplication.getContext(), adRequest);
    }

    public static void initADS() {
        interstitialAd = new InterstitialAd(MyApplication.getContext());
        if (BuildConfig.DEBUG) interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        else interstitialAd.setAdUnitId(Constant.ADS.INTER_MAIN);

        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

        interstitialAd.setAdListener(
                new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        Log.d("Adlistener", "Message");
                        //   Toast.makeText(MyActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                        Log.d("AdListener1", "Message");
                        loadAds.onOk();
                    }

                    @Override
                    public void onAdClosed() {
                        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
                            AdRequest adRequest = new AdRequest.Builder().build();
                            interstitialAd.loadAd(adRequest);
                        }
                        loadAds.onOk();
                        Log.d("onAdClosed", "Message");
                    }
                });
    }

    public static void showBanner(AdSize adSize, ViewGroup adContainer) {
        AdView mAdView = new AdView(MyApplication.getContext());
        mAdView.setAdSize(adSize);
        if (BuildConfig.DEBUG) mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        else mAdView.setAdUnitId(Constant.ADS.BANNER_MAIN);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        adContainer.addView(mAdView, layoutParams);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public static void showInterstitial(int i, LoadAds ads) {
        Random random = new Random();
        int x = random.nextInt(101);
        if (i <= x) {
            if (interstitialAd != null && interstitialAd.isLoaded()) {
                interstitialAd.show();
                loadAds = ads;
            } else {
                try {
                    if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
                        AdRequest adRequest = new AdRequest.Builder().build();
                        interstitialAd.loadAd(adRequest);
                    }
                } catch (Exception e) {
                }
            }
        } else {
            ads.onOk();
        }

    }
}


