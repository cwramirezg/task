package com.gmail.cwramirezg.task.features.login;

import com.gmail.cwramirezg.task.data.PreferenceManager;
import com.gmail.cwramirezg.task.data.source.DataSourceRepository;
import com.gmail.cwramirezg.task.features.shared.BasePresenter;

import javax.inject.Inject;

class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter(PreferenceManager preferenceManager, DataSourceRepository dataSourceRepository) {
        dataSourceRepository.setOnlineMode(preferenceManager.getConfig().isBatch());
    }

    @Override
    public void attachView(LoginContract.View mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onLogin() {
        getView().goToMainActivity();
    }
}