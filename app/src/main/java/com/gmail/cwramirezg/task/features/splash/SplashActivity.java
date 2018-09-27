package com.gmail.cwramirezg.task.features.splash;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.gmail.cwramirezg.task.R;
import com.gmail.cwramirezg.task.features.main.MainNavigationActivity;
import com.gmail.cwramirezg.task.features.shared.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashActivity extends BaseActivity implements SplashContract.View {

    private static final int MILLIS_WAIT = 1500;

    @BindView(R.id.version)
    TextView version;

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        presenter.attachView(this);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> presenter.onSplashDone(), MILLIS_WAIT);

        try {
            version.setText(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version.setText("0.0");
        }
    }

    @Override
    public void goToMainActivity() {
        startActivity(MainNavigationActivity.newInstance(getContext()));
        finish();
    }
}
