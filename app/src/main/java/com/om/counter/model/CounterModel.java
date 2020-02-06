package com.om.counter.model;

import androidx.lifecycle.ViewModel;

public class CounterModel extends ViewModel {

    private int value = 0;

    public int getValue() {
       return value;
    }

    public void increase() {
        this.value++;
    }

    public void decrease() {
        if (value > 0) this.value--;
    }
}
