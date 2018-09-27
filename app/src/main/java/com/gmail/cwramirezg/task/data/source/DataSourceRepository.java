package com.gmail.cwramirezg.task.data.source;

import android.support.annotation.NonNull;

import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.data.pojos.MensajeResponse;
import com.gmail.cwramirezg.task.injection.annotations.ApplicationScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

@ApplicationScope
public class DataSourceRepository implements DataSource.Shared, DataSource.Remote, DataSource.Local {

    private final DataSourceLocal local;
    private final DataSourceRemote remote;

    private boolean online;

    @Inject
    public DataSourceRepository(@NonNull DataSourceLocal local,
                                @NonNull DataSourceRemote remote) {
        this.local = local;
        this.remote = remote;
    }

    public void setOnlineMode(boolean online) {
        this.online = online;
    }

    private DataSource.Shared getSource() {
        return online ? remote : local;
    }

    @Override
    public Observable<List<Task>> listTask() {
        if (online) {
            return remote.listTask();
        } else {
            return local.listTask();
        }
    }

    @Override
    public Observable<MensajeResponse> addTask(Task task) {
        if (online) {
            return remote.addTask(task);
        } else {
            return local.addTask(task);
        }
    }

    @Override
    public Completable completeTask(Task task) {
        if (online) {
            return remote.completeTask(task);
        } else {
            return local.completeTask(task);
        }
    }

    @Override
    public Completable deleteTask(Task task) {
        if (online) {
            return remote.deleteTask(task);
        } else {
            return local.deleteTask(task);
        }
    }
}
