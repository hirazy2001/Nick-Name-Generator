package com.example.nicknamegeneradorpro.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicknamegeneradorpro.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;

public class InterstitialAdmobActivity extends AppCompatActivity {

    private static final long GAME_LENGTH_MILLISECONDS = 3000;
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

    private InterstitialAd interstitialAd;
    private CountDownTimer countDownTimer;
    private Button retryButton;
    private boolean gameIsInProgress;
    private long timerMilliseconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial_admob);
        init();
        interstitialAd.setAdUnitId(AD_UNIT_ID);
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                Toast.makeText(InterstitialAdmobActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                startGame();
            }
        });
//        startGame();
//        AdRequest adRequest = new AdRequest.Builder().build();
//        interstitialAd.loadAd(adRequest);
//        showInterstitial();
        retryButton = findViewById(R.id.retry_button);
        retryButton.setVisibility(View.INVISIBLE);
        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
                finish();
            }
        });
        startGame();
    }

    public void init(){

        interstitialAd = new InterstitialAd(this);
        retryButton = findViewById(R.id.retry_button);
    }

    public void  startGame(){
        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }

        retryButton.setVisibility(View.INVISIBLE);
        resumeGame(GAME_LENGTH_MILLISECONDS);
    }
    private void createTimer(final long milliseconds) {
        // Create the game timer, which counts down to the end of the level
        // and shows the "retry" button.
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        final TextView textView = findViewById(R.id.timer);

        countDownTimer = new CountDownTimer(milliseconds, 50) {
            @Override
            public void onTick(long millisUnitFinished) {
                timerMilliseconds = millisUnitFinished;
                textView.setText("seconds remaining: " + ((millisUnitFinished / 1000) + 1));
            }

            @Override
            public void onFinish() {
                gameIsInProgress = false;
                textView.setText("done!");
                retryButton.setVisibility(View.VISIBLE);
            }
        };
    }

    private void resumeGame(long gameLengthMilliseconds) {
        gameIsInProgress = true;
        timerMilliseconds = gameLengthMilliseconds;
        createTimer(timerMilliseconds);
        countDownTimer.start();
    }
    private void showInterstitial() {
         //Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            startGame();
        }
        //interstitialAd.show();
    }
    @Override
    protected void onPause() {
        countDownTimer.cancel();
        super.onPause();
    }
}