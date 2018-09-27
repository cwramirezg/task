package com.gmail.cwramirezg.task.data.source;

import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.data.pojos.MensajeResponse;
import com.gmail.cwramirezg.task.data.source.local.AppDatabase;
import com.gmail.cwramirezg.task.injection.annotations.ApplicationScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

@ApplicationScope
public class DataSourceLocal implements DataSource.Shared, DataSource.Local {

    private AppDatabase appDatabase;

    @Inject
    public DataSourceLocal(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public Observable<List<Task>> listTask() {
        return appDatabase.taskDao().getAll().toObservable();
    }

    @Override
    public Observable<MensajeResponse> addTask(Task task) {
        return Observable.create(emitter -> {
            try {
                appDatabase.taskDao().insert(task);
                emitter.onNext(new MensajeResponse(1, task.getName()));
                emitter.onComplete();
            } catch (Exception e) {
                e.printStackTrace();
                emitter.onError(e);
            }
        });
    }

    @Override
    public Completable completeTask(Task task) {
        return Completable.fromAction(() -> {
            task.setStatus("1");
            appDatabase.taskDao().update(task);
        });
    }

    @Override
    public Completable deleteTask(Task task) {
        return Completable.fromAction(() -> appDatabase.taskDao().delete(task));
    }
}
