package com.gmail.cwramirezg.task.features.login;

import com.gmail.cwramirezg.task.features.shared.BaseContractView;

interface LoginContract {

    interface View extends BaseContractView {
        void goToMainActivity();
    }

    interface Presenter {
        void onLogin();
    }
}
