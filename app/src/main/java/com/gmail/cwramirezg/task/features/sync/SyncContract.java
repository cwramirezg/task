package com.gmail.cwramirezg.task.features.sync;

import com.gmail.cwramirezg.task.features.shared.BaseContractView;

interface SyncContract {

    interface View extends BaseContractView {

        void setupDialog(String title, String message, boolean download);

        void startSync();

        void updateProgress(int doneItems, int totalItems);

        void syncSuccess();

        void syncError(String message);
    }

    interface Presenter {
        void setupView();

        void startSync();

        void syncSuccess();
    }
}