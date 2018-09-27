package com.gmail.cwramirezg.task.utils.interfaces;

import android.text.Editable;

public interface TextWatcher extends android.text.TextWatcher {

    @Override
    default void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    default void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    default void afterTextChanged(Editable s) {
        onChanged(s.toString());
    }

    void onChanged(String s);
}