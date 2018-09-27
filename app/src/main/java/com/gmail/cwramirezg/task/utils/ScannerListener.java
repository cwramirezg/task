package com.gmail.cwramirezg.task.utils;

import android.view.KeyEvent;
import android.view.View;

public interface ScannerListener extends View.OnKeyListener {

    void onEnter(View view);

    @Override
    default boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                onEnter(v);
            }
            return false;
        }
        return false;
    }
}