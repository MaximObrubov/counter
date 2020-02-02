package com.om.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int counter = 0;
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
     * Redefine carret behaviour
     * @param event
     * @return boolean is success
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();



        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_UP) increaseCounter();
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN) decreaseCounter();
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.options:
                Toast.makeText(this, "Options", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;
            case R.id.about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
//TODO: about app xml
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
    }

    private void increaseCounter() {
        this.counter++;
        this.indicator.setText(String.valueOf(this.counter));
    }

    private void decreaseCounter() {
        if (this.counter > 0) this.counter--;
        this.indicator.setText(String.valueOf(this.counter));
    }
}
