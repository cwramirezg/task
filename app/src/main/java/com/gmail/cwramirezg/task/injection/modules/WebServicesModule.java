package com.gmail.cwramirezg.task.injection.modules;

import com.gmail.cwramirezg.task.data.PreferenceManager;
import com.gmail.cwramirezg.task.data.source.remote.WebServices;
import com.gmail.cwramirezg.task.injection.annotations.ApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class WebServicesModule {

    public WebServicesModule() {

    }

    @Provides
    @ApplicationScope
    public WebServices webServices(Retrofit retrofit) {
        return retrofit.create(WebServices.class);
    }

    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    public Retrofit retrofit(Gson gson, OkHttpClient okHttpClient, PreferenceManager preferenceManager) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(preferenceManager.getBaseUrl())
                .build();
    }
}
