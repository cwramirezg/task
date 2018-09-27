package com.gmail.cwramirezg.task.data.source.local.dao;

import java.util.List;


public interface BaseDao<T> {

    long insert(T entity);

    long[] insertAll(List<T> entities);

    void deleteAll();

}
