package com.om.counter.controllers;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;

import com.om.counter.MainActivity;
import com.om.counter.R;
import com.om.counter.model.CounterModel;


public class CounterController {
    private int VIBRATION_TIME = 30;
    private boolean isVibro;
    private TextView view;
    private Context ctx;
    private CounterModel model;

    public CounterController(MainActivity activity, Context ctx) {
        this.isVibro = activity.isVibro();
        this.model = activity.getCounterModel();
        this.view = activity.findViewById(R.id.counter);
        this.ctx = ctx;
    }

    public void increase() {
        model.increase();
        updateView();
        vibrate();
    }

    public void descrease() {
        model.decrease();
        updateView();
        vibrate();
    }

    public void reset() {
        model.reset();
        updateView();
    }

    private void updateView() {
        view.setText(String.valueOf(model.getValue()));
    }


    private void vibrate() {
        if (!this.isVibro) return;

        Vibrator v = (Vibrator) this.ctx.getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(VIBRATION_TIME, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            // NOTE: deprecated in API 26
            v.vibrate(VIBRATION_TIME);
        }
    }

}
