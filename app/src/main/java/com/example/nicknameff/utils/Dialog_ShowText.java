package com.example.nicknameff.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.nicknameff.R;
import com.nangopro.ads.NangoADS;

public class Dialog_ShowText extends Dialog {

    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
    TextView content;
    LinearLayout close;
    Context context;
    NangoADS nangoADS;

    public Dialog_ShowText(@NonNull Context context) {
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
        nangoADS = new NangoADS();
        setCancelable(false);
        init();


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        startGame();
    }
    public void init(){

    }
    public void  startGame(){

    }
    public void setContent(String s){
        content.setText(s);
    }
}
