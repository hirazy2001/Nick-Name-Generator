package com.example.nicknamegeneradorpro.utils;

import java.util.HashMap;
import java.util.Map;

public class AsignedFont {

    public Map<Integer, String> map(int position) {

        Map<Integer, String> res = new HashMap<>();
        String font = Constants.font(position);
        int i2 = 0;
        for (int i = 65; i <= 122; i++) {
            if (i <= 90) {
                res.put(i, String.valueOf(font.charAt(i2)));
                i2++;
            }
            if (i >= 97){
                res.put(i, String.valueOf(font.charAt(i2)));
                i2++;
            }
        }
        return res;
    }
}
