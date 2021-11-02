package com.example.nicknameff.ui;

import android.content.Intent;

import com.nangopro.ui.NangoLauncherActivity;

public class M extends NangoLauncherActivity {
    @Override
    public void onMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
