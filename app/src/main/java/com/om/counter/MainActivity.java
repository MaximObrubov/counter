package com.om.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
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

//        Toast.makeText(this, String.valueOf(action), Toast.LENGTH_SHORT).show();

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
