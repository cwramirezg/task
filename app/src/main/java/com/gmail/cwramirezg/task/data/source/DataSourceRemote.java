package com.gmail.cwramirezg.task.data.source;

import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.data.pojos.MensajeResponse;
import com.gmail.cwramirezg.task.data.source.remote.WebServices;
import com.gmail.cwramirezg.task.injection.annotations.ApplicationScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

@ApplicationScope
public class DataSourceRemote implements DataSource.Shared, DataSource.Remote {

    private WebServices webServices;

    @Inject
    public DataSourceRemote(WebServices webServices) {
        this.webServices = webServices;
    }

    @Override
    public Observable<List<Task>> listTask() {
        return null;
    }

    @Override
    public Observable<MensajeResponse> addTask(Task task) {
        return null;
    }

    @Override
    public Completable deleteTask(Task task) {
        return null;
    }

    @Override
    public Completable completeTask(Task task) {
        return null;
    }
}
