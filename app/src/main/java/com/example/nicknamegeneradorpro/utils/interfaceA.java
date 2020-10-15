package com.example.nicknamegeneradorpro.utils;

import android.app.Activity;
import android.content.Intent;

public class interfaceA {
    public static void startActivity(Activity a, Class c, String data){
        Intent intent = new Intent(a, c);
        intent.putExtra("dfdf","dfdsf");
        a.startActivity(intent);
    }
}
