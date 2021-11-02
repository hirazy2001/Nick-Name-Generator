package com.example.nicknameff.utils;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class TimeUtils {
    static int i = 0;

    public static void setTimeout(int time, CallBack callBack) {
        new Handler().postDelayed(() -> {
            callBack.callBack();
        }, time);
    }

    public static void setTimeinterval(int period, CallBack callBack) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                i += 20;
                if (i <= 100) {
                    callBack.callBack(i);
                } else {
                    callBack.callBack();
                    timer.cancel();
                }
            }
        }, 0, period);
    }


    public interface CallBack {
        void callBack();

        void callBack(int i);

    }
}
