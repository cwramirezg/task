package com.gmail.cwramirezg.task.features.splash;

import com.gmail.cwramirezg.task.features.shared.BaseContractView;

interface SplashContract {

    interface View extends BaseContractView {
        void goToMainActivity();
    }

    interface Presenter {
        void onSplashDone();
    }
}