package com.om.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int counter = 0;
    TextView indicator;

    public void increaseCounter(View view) {
        counter++;
        Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_SHORT).show();
        this.indicator.setText(String.valueOf(counter));
    }

    public void decreaseCounter(View view) {
        if (counter > 0) counter--;
        this.indicator.setText(String.valueOf(counter));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.indicator = (TextView) findViewById(R.id.textview);
    }


}
