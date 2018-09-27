package com.gmail.cwramirezg.task.features.splash;

import com.gmail.cwramirezg.task.data.PreferenceManager;
import com.gmail.cwramirezg.task.data.source.DataSourceRepository;
import com.gmail.cwramirezg.task.features.shared.BasePresenter;

import javax.inject.Inject;


class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    @Inject
    public SplashPresenter(PreferenceManager preferenceManager, DataSourceRepository dataSourceRepository) {
        dataSourceRepository.setOnlineMode(preferenceManager.getConfig().isBatch());
    }

    @Override
    public void attachView(SplashContract.View mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onSplashDone() {
        getView().goToMainActivity();
    }
}