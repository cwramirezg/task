package com.gmail.cwramirezg.task.injection;

import android.content.Context;

import com.gmail.cwramirezg.task.data.PreferenceManager;
import com.gmail.cwramirezg.task.data.source.DataSourceRepository;
import com.gmail.cwramirezg.task.data.source.local.AppDatabase;
import com.gmail.cwramirezg.task.data.source.remote.WebServices;
import com.gmail.cwramirezg.task.injection.annotations.ApplicationScope;
import com.gmail.cwramirezg.task.injection.modules.SqliteModule;
import com.gmail.cwramirezg.task.injection.modules.WebServicesModule;

import dagger.Component;

@Component(modules = {WebServicesModule.class, SqliteModule.class})
@ApplicationScope
public interface AppComponent {

    WebServices webServices();

    PreferenceManager preferenceManager();

    DataSourceRepository dataSourceRepository();

    AppDatabase appDatabase();

    Context context();

}
