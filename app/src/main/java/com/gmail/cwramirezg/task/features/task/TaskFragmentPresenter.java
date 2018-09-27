package com.gmail.cwramirezg.task.features.task;

import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.data.source.DataSourceRepository;
import com.gmail.cwramirezg.task.features.shared.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class TaskFragmentPresenter extends BasePresenter<TaskFragmentContract.View> implements TaskFragmentContract.Presenter {
    private final DataSourceRepository dataSourceRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public TaskFragmentPresenter(DataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
    }

    @Override
    public void attachView(TaskFragmentContract.View mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        disposables.clear();
    }

    @Override
    public void showTasks() {
        disposables.add(dataSourceRepository.listTask()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tasks -> getView().showTasks(tasks),
                        throwable -> getView().showError(throwable.getMessage())));
    }

    @Override
    public void completeTask(Task task) {
        disposables.add(dataSourceRepository.completeTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getView().showMessage("El registro se actualizo"),
                        throwable -> getView().showError(throwable.getMessage())));
    }

    @Override
    public void deleteTask(Task task) {
        disposables.add(dataSourceRepository.deleteTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

}
