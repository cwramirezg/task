package com.gmail.cwramirezg.task.features.task.add;

import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.data.source.DataSourceRepository;
import com.gmail.cwramirezg.task.features.shared.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class AddTaskFragmentPresenter extends BasePresenter<AddTaskFragmentContract.View> implements AddTaskFragmentContract.Presenter {
    private final DataSourceRepository dataSourceRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public AddTaskFragmentPresenter(DataSourceRepository dataSourceRepository) {
        this.dataSourceRepository = dataSourceRepository;
    }

    @Override
    public void attachView(AddTaskFragmentContract.View mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        disposables.clear();
    }

    @Override
    public void addTask(Task task) {
        disposables.add(dataSourceRepository.addTask(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mensajeResponse -> getView().showMessage("El registro fue agregado"),
                        throwable -> getView().showError(throwable.getMessage())));
    }

}
