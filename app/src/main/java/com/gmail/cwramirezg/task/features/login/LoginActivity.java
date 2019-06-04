package com.gmail.cwramirezg.task.features.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.gmail.cwramirezg.task.R;
import com.gmail.cwramirezg.task.features.main.MainNavigationActivity;
import com.gmail.cwramirezg.task.features.shared.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.bt_login)
    TextView btLogin;

    @Inject
    LoginPresenter presenter;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter.attachView(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    @OnClick({R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                presenter.onLogin();
                break;
        }
    }

    @Override
    public void goToMainActivity() {
        startActivity(MainNavigationActivity.newInstance(getContext()));
        finish();
    }

}
