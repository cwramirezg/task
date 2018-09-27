package com.gmail.cwramirezg.task.utils;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public abstract class MySingleObserver<T> implements SingleObserver<T> {
    @Override
    public void onSubscribe(Disposable d) {
        Timber.e("onSubscribe");
    }

    @Override
    public void onError(Throwable e) {
        Timber.e("onError %s", e.getCause());
    }
}
