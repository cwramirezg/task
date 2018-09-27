package com.gmail.cwramirezg.task.injection.modules;

import android.content.Context;

import com.gmail.cwramirezg.task.injection.annotations.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @ApplicationScope
    public Context context() {
        return context;
    }
}
