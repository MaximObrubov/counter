package com.om.counter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.om.counter.MainActivity;
import com.om.counter.R;

public class PlusMinusButtons extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainActivity main = (MainActivity) getActivity();
        int layout = main.isLefty() ? R.layout.plus_minus_buttons_lefty : R.layout.plus_minus_buttons;
        return inflater.inflate(layout, container, false);
    }
}
