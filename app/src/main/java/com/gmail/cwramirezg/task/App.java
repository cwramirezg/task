package com.gmail.cwramirezg.task;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.gmail.cwramirezg.task.injection.AppComponent;
import com.gmail.cwramirezg.task.injection.DaggerAppComponent;
import com.gmail.cwramirezg.task.injection.modules.ContextModule;
import com.gmail.cwramirezg.task.utils.UtilMethods;

import timber.log.Timber;

public class App extends Application {

    private AppComponent appComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        UtilMethods.initialize(getApplicationContext());
        buildDependecyInjection();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            setStrictMode();
        }
    }

    public void buildDependecyInjection() {
        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}
