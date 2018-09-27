package com.gmail.cwramirezg.task.features.shared;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.gmail.cwramirezg.task.App;
import com.gmail.cwramirezg.task.injection.ActivityComponent;
import com.gmail.cwramirezg.task.injection.DaggerActivityComponent;


public abstract class BaseActivity extends AppCompatActivity {

    protected View lyProgress;
    private ActivityComponent activityComponent;

    protected void replaceFragment(Fragment fragment, @IdRes int layoutId) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(layoutId, fragment)
                .commit();
    }

    public void showLoading() {
        if (lyProgress == null) return;
        lyProgress.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        if (lyProgress == null) return;
        lyProgress.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        activityComponent = DaggerActivityComponent.builder()
                .appComponent(App.get(this).getAppComponent())
                .build();

        setupVariables();
    }

    protected void buildComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(App.get(this).getAppComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    protected void setupVariables() {
    }

    protected Context getContext() {
        return this;
    }

}
