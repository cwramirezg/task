package com.gmail.cwramirezg.task.features.shared;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.gmail.cwramirezg.task.injection.ActivityComponent;

public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupVariables();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }

    protected void setupVariables() {

    }

    protected void setupViews(View view) {

    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    protected ActivityComponent getActivityComponent() {
        return getBaseActivity().getActivityComponent();
    }

    /**
     * Si el valor devuelto es TRUE, se continua con la ejecucion del onBackPressed del Activity
     *
     * @return
     */
    public boolean onBackPressed() {
        return true;
    }
}
