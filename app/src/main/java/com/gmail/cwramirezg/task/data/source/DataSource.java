package com.gmail.cwramirezg.task.data.source;

import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.data.pojos.MensajeResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface DataSource {

    interface Shared {

        Observable<List<Task>> listTask();

        Observable<MensajeResponse> addTask(Task task);

        Completable completeTask(Task task);

        Completable deleteTask(Task task);

    }

    interface Remote {


    }

    interface Local {


    }

}