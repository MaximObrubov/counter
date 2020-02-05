package com.om.counter;

import androidx.appcompat.app.AppCompatActivity;
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


    private int VIBRATION_TIME = 50;
    private int counter = 0;
    private SharedPreferences prefs;
    private TextView indicator;

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
        boolean useHardware = this.prefs.getBoolean("hardware_buttons", true);

        switch (keyCode) {
        case KeyEvent.KEYCODE_VOLUME_UP:
            if (action == KeyEvent.ACTION_UP && useHardware) {
                increaseCounter();
            }
            return true;
        case KeyEvent.KEYCODE_VOLUME_DOWN:
            if (action == KeyEvent.ACTION_DOWN && useHardware) {
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
    }


    private void setupSharedPreferences() {
        if (this.prefs == null) {
            this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
            this.prefs.registerOnSharedPreferenceChangeListener(this);
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.prefs = sharedPreferences;
    }


    private void increaseCounter() {
        this.counter++;
        this.indicator.setText(String.valueOf(this.counter));
        if (prefs.getBoolean("vibration", true)) vibrate();
    }


    private void decreaseCounter() {
        if (this.counter > 0) this.counter--;
        this.indicator.setText(String.valueOf(this.counter));
        if (prefs.getBoolean("vibration", true)) vibrate();
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
