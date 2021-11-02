package com.example.nicknameff.model;

import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class TextC implements Serializable {
    String text;

    public TextC(){};

    public TextC(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, TextC.class);
    }
}
