package com.om.counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.VibrationEffect;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    int VIBRATION_TIME = 100;
    int counter = 0;
    boolean useHardware;
    boolean useVibration;
    TextView indicator;

    /**
     * Increase counter from a view
     * @param view
     */
    public void increaseCounter(View view) {
        increaseCounter();
    }

    /**
     * Descrease counter from a view
     * @param view
     */
    public void decreaseCounter(View view) {
        decreaseCounter();
    }


    /**
     * Redefine volume curret behaviour
     * @param event
     * @return boolean is success
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        Toast.makeText(this, String.valueOf(this.useHardware), Toast.LENGTH_SHORT).show();

        switch (keyCode) {
        case KeyEvent.KEYCODE_VOLUME_UP:
            if (action == KeyEvent.ACTION_UP && this.useHardware) {
                increaseCounter();
            }
            return true;
        case KeyEvent.KEYCODE_VOLUME_DOWN:
            if (action == KeyEvent.ACTION_DOWN && this.useHardware) {
                decreaseCounter();
            }
            return true;
        default:
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.options:
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        case R.id.about:
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.indicator = (TextView) findViewById(R.id.textview);
        setupSharedPreferences();

        Toast.makeText(this, "On create", Toast.LENGTH_SHORT).show();
    }


    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

//        TODO: check here to find why options are reset
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if (key.equals("hardware_buttons")) {
            this.useHardware = sharedPreferences.getBoolean("hardware_buttons", true);
            Toast.makeText(this, key + " " + sharedPreferences.getBoolean("hardware_buttons", true) + " " + this.useHardware, Toast.LENGTH_SHORT).show();
        } else if (key.equals("vibration")) {
            Toast.makeText(this, key + sharedPreferences.getBoolean("vibration", true), Toast.LENGTH_SHORT).show();
            this.useVibration = sharedPreferences.getBoolean("vibration", true);
        }

    }


    private void increaseCounter() {
        this.counter++;
        this.indicator.setText(String.valueOf(this.counter));
        if (this.useVibration) vibrate();
    }


    private void decreaseCounter() {
        if (this.counter > 0) this.counter--;
        this.indicator.setText(String.valueOf(this.counter));
        if (this.useVibration) vibrate();
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(VIBRATION_TIME, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            // NOTE: deprecated in API 26
            v.vibrate(VIBRATION_TIME);
        }
    }
}
