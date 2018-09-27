package com.gmail.cwramirezg.task.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.gmail.cwramirezg.task.data.models.Task;
import com.gmail.cwramirezg.task.data.source.local.dao.TaskDao;

@Database(entities = {
        Task.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

}
