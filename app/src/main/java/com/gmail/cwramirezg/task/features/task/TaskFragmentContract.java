package com.gmail.cwramirezg.task.features.task;

import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.features.shared.BaseContractView;

import java.util.List;

public interface TaskFragmentContract {
    interface View extends BaseContractView {
        void showTasks(List<Task> tasks);

        void showMessage(String message);

        void showError(String message);
    }

    interface Presenter {
        void showTasks();

        void completeTask(Task task);

        void deleteTask(Task task);
    }
}
