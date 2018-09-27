package com.gmail.cwramirezg.task.data.source.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gmail.cwramirezg.task.data.models.Task;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface TaskDao extends BaseDao<Task> {

    @Query("DELETE  FROM Task")
    void deleteAll();

    @Query("SELECT * FROM Task")
    Flowable<List<Task>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Task entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Task> entities);

    @Update
    void update(Task entity);

    @Delete
    void delete(Task entity);

//    --------------
//    Custom Queries
//    --------------

}
