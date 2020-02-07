package com.om.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.om.counter.controllers.CounterController;

public class MainActivity
        extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences prefs;
    private CounterController counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupSharedPreferences();
        boolean isDark = this.prefs.getBoolean("is_dark", false);
        setContentView(isDark ? R.layout.activity_main_dark : R.layout.activity_main);
        counter = new CounterController(MainActivity.this, this);
        counter.updateView();
    }

    public boolean isVibro() {
        return this.prefs.getBoolean("vibration", false);
    }

    /**
     * Increase counter from a view
     * @param view
     */
    public void increaseCounter(View view) {
        counter.increase();
    }

    /**
     * Descrease counter from a view
     * @param view
     */
    public void decreaseCounter(View view) {
        counter.descrease();
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
                this.counter.increase();
            }
            return true;
        case KeyEvent.KEYCODE_VOLUME_DOWN:
            if (action == KeyEvent.ACTION_DOWN && useHardware) {
                this.counter.descrease();
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
        case R.id.action_reset:
            this.counter.reset();
            return true;
        case R.id.action_save:
            Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
            return true;
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


    private void setupSharedPreferences() {
        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
        this.prefs.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.prefs = sharedPreferences;
    }

}
