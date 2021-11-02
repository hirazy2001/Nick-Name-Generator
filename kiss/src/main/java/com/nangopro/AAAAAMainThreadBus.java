package com.nangopro;

import android.os.Handler;
import android.os.Looper;

import org.greenrobot.eventbus.EventBus;


public class AAAAAMainThreadBus extends EventBus {
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    AAAAAMainThreadBus.super.post(event);
                }
            });
        }
    }
}
