package com.gmail.cwramirezg.task.data.source.remote;

import com.gmail.cwramirezg.task.data.models.Task;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServices {

    @GET(Urls.GET_LIST_TASK)
    Observable<List<Task>> listAllTask();

    @POST(Urls.POST_TASK)
    Observable<Task> loginUsuario(@Body Task task);

    @DELETE(Urls.DELETE_TASK)
    Comparable deleteTask(@Body Task task);
}