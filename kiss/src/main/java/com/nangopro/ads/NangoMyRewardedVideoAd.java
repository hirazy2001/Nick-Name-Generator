package com.nangopro.ads;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import androidx.annotation.NonNull;


import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.nangopro.BuildConfig;
import com.nangopro.AAAAConfigApplicaion;
import com.nangopro.obj.NangoOConfig;


public class NangoMyRewardedVideoAd {
    public interface AdCallback {
        void doNext();
    }

    private RewardedAd rewardedAd;
    Activity activity;
    RewardedAdLoadCallback adLoadCallback;
    ProgressDialog progressBar;

    NangoOConfig nangoOConfig;

    public NangoMyRewardedVideoAd(Activity activity) {
        nangoOConfig = AAAAConfigApplicaion.getCOnfig();
        this.activity = activity;
        progressBar = new ProgressDialog(activity);
        progressBar.setMessage("Loadding Ads, please wait.");
        progressBar.setCancelable(false);
        progressBar.show();


    }

    public void loadRewardedVideoAd(Activity context, AdCallback callback) {


        if (BuildConfig.DEBUG) {
            rewardedAd = new RewardedAd(activity,
                    "ca-app-pub-3940256099942544/5224354917");
        } else {
            rewardedAd = new RewardedAd(activity,
                    nangoOConfig.ads.admod_rewarded);
        }

        adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                // Ad successfully loaded.
                if (rewardedAd.isLoaded()) {

                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onUserEarnedReward(@NonNull com.google.android.gms.ads.rewarded.RewardItem rewardItem) {
                            Log.e("loadRewardedVideoAd", "DONE");
                            progressBar.dismiss();

                            callback.doNext();

                        }

                        @Override
                        public void onRewardedAdClosed() {
                            progressBar.dismiss();
                            super.onRewardedAdClosed();
                        }
                    };
                    rewardedAd.show(context, adCallback);
                } else {
                    progressBar.dismiss();
                    Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                }
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                // Ad failed to load.
                progressBar.dismiss();
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

    }


}
