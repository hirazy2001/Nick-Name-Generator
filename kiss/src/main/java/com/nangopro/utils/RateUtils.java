package com.nangopro.utils;

import android.app.Activity;

import vn.aib.ratedialog.RatingDialog;

public class RateUtils {
    public static void showRateDialog(Activity context, String email) {
        int count = SharedPrefsUtils.getInstance(context).getInt("rate");

        if (count < 2) {
            RatingDialog ratingDialog = new RatingDialog(context);
            ratingDialog.setRatingDialogListener(new RatingDialog.RatingDialogInterFace() {
                @Override
                public void onDismiss() {

                }

                @Override
                public void onSubmit(float v) {
                    if (v >= 4) {
                        int count = SharedPrefsUtils.getInstance(context).getInt("rate");
                        count++;
                        SharedPrefsUtils.getInstance().putInt("rate", count);
                        AppUtils.rateApp(context);
                    } else {
                       if(!email.equals("")) AppUtils.shareEmail(context, email);
                    }

                }

                @Override
                public void onRatingChanged(float v) {

                }
            });
            ratingDialog.showDialog();
        }


    }
}
