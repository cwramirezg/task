package com.gmail.cwramirezg.task.injection.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.gmail.cwramirezg.task.BuildConfig;
import com.gmail.cwramirezg.task.data.source.local.AppDatabase;
import com.gmail.cwramirezg.task.injection.annotations.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module()
public class SqliteModule {

    @ApplicationScope
    @Provides
    public AppDatabase appDatabase(@ApplicationScope Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, BuildConfig.DB_NAME)
                .allowMainThreadQueries()
                .build();
    }
}
