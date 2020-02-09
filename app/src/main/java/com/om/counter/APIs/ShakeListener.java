package com.om.counter.APIs;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import com.om.counter.MainActivity;
import com.om.counter.controllers.CounterController;

public class ShakeListener implements SensorEventListener {

    public float acceleration;
    private final long DELAY = 700;
    private float lastAcceleration;
    private float currentAcceleration;
    private Context ctx;
    private CounterController counter;
    private long lastShakeTime;

    public ShakeListener(MainActivity activity, float acceleration, float lastAcceleration, float currentAcceleration) {
        this.acceleration = acceleration;
        this.lastAcceleration = lastAcceleration;
        this.currentAcceleration = currentAcceleration;
        this.ctx = activity.getApplicationContext();
        this.counter = activity.getCounter();
        lastShakeTime = System.currentTimeMillis();
    }


    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        long now = System.currentTimeMillis();
        lastAcceleration = currentAcceleration;
        currentAcceleration = (float) Math.sqrt((double) (x * x + y * y + z * z));
        float delta = currentAcceleration - lastAcceleration;
        acceleration = acceleration * 0.9f + delta;
        if (acceleration > 12 && (lastShakeTime + DELAY) < now ) {
            counter.increase();
            lastShakeTime = now;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
