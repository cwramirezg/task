package com.gmail.cwramirezg.task.injection;

import com.gmail.cwramirezg.task.features.main.MainNavigationActivity;
import com.gmail.cwramirezg.task.features.splash.SplashActivity;
import com.gmail.cwramirezg.task.features.sync.SyncDialog;
import com.gmail.cwramirezg.task.features.task.TaskFragment;
import com.gmail.cwramirezg.task.features.task.add.AddTaskFragment;
import com.gmail.cwramirezg.task.injection.annotations.ActivityScope;
import com.gmail.cwramirezg.task.injection.modules.ActivityModule;

import dagger.Component;

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(MainNavigationActivity activity);

    void inject(TaskFragment fragment);

    void inject(AddTaskFragment fragment);

    void inject(SyncDialog dialog);

}
