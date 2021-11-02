package com.example.nicknameff.utils;

import com.example.nicknameff.model.TextC;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonUtils<T> {

    public String arrayToString(ArrayList<T> list) {
        Gson g = new Gson();
        Type listType = new TypeToken<List<TextC>>() {
        }.getType();
        return g.toJson(list, listType);
    }

    public ArrayList<T> stringToArray(String s) {
        Gson g = new Gson();
        Type listType = new TypeToken<ArrayList<TextC>>() {
        }.getType();
        ArrayList<T> list = g.fromJson(s, listType);
        return list;
    }

    public T stringToObj(String s) {
        Gson g = new Gson();
        Type listType = new TypeToken<T>() {
        }.getType();
        T o = g.fromJson(s, listType);
        return o;
    }
}
