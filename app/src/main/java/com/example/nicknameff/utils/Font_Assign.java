package com.example.nicknameff.utils;

import java.util.HashMap;
import java.util.Map;

public class Font_Assign {

    public Map<Integer, String> map(int position) {

        Map<Integer, String> res = new HashMap<>();
        String font = Text_Constants.font(position);
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
