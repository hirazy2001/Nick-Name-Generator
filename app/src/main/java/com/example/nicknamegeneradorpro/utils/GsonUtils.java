package com.example.nicknamegeneradorpro.utils;

import com.example.nicknamegeneradorpro.model.TextCoppy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonUtils<T> {

    public String arrayToString(ArrayList<T> list) {
        Gson g = new Gson();
        Type listType = new TypeToken<List<TextCoppy>>() {
        }.getType();
        return g.toJson(list, listType);
    }

    public ArrayList<T> stringToArray(String s) {
        Gson g = new Gson();
        Type listType = new TypeToken<ArrayList<TextCoppy>>() {
        }.getType();
        ArrayList<T> list = g.fromJson(s, listType);
        return list;
    }
}
