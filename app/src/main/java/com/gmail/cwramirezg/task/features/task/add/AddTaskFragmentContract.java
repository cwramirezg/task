package com.gmail.cwramirezg.task.features.task.add;

import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.features.shared.BaseContractView;

public interface AddTaskFragmentContract {
    interface View extends BaseContractView {
        void showMessage(String message);

        void showError(String message);

    }

    interface Presenter {

        void addTask(Task task);

    }
}
