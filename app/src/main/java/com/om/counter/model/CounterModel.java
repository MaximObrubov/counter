package com.om.counter.model;

import android.content.Context;
import android.content.SharedPreferences;

public class CounterModel {
    private final String PREFS_NAME = "default";
    private SharedPreferences prefs;
    private int value;

    public CounterModel(Context ctx) {
        prefs = ctx.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        value = prefs.getInt("value", 0);
    }

    public int getValue() {
       return value;
    }

    public void increase() {
        this.value++;
        update();
    }

    public void decrease() {
        if (value > 0) this.value--;
        update();
    }

    public void reset() {
        value = 0;
        update();
    }

    private void update() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("value", value);
        editor.apply();
    }
}