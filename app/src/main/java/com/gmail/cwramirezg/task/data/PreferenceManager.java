package com.gmail.cwramirezg.task.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.gmail.cwramirezg.task.BuildConfig;
import com.gmail.cwramirezg.task.data.pojos.Configuracion;
import com.gmail.cwramirezg.task.injection.annotations.ApplicationScope;
import com.gmail.cwramirezg.task.utils.Constants;
import com.google.gson.Gson;

import javax.inject.Inject;

@ApplicationScope
public class PreferenceManager {

    private final SharedPreferences mPreferences;

    @Inject
    public PreferenceManager(@ApplicationScope Context context) {
        mPreferences = context.getSharedPreferences(BuildConfig.PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getBaseUrl() {
        return String.format("%s/api/v1/", getConfig().getServidor());
    }

    public void removeUserConfig() {
        Configuracion config = getConfig();
        config.setBatch(true);
        saveConfig(config);
    }

    public void saveConfig(Configuracion configuracion) {
        String configString = new Gson().toJson(configuracion, Configuracion.class);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(Constants.PREF_CONFIG, configString);
        editor.apply();
    }

    public Configuracion getConfig() {
        String configString = mPreferences.getString(Constants.PREF_CONFIG, "");

        if (TextUtils.isEmpty(configString)) {
            Configuracion config = new Configuracion();
            saveConfig(config);
            return config;
        } else {
            return new Gson().fromJson(configString, Configuracion.class);
        }

    }

    public void removeFlags() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(Constants.PREF_FLAG_FIRST);
        editor.remove(Constants.PREF_FLAG_BD_SYNCED);
        editor.apply();
    }

    public void saveFlag(String flagName) {
        saveFlag(flagName, true);
    }

    public void saveFlag(String flagName, boolean flag) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(flagName, flag);
        editor.apply();
    }

    public boolean getFlag(String flagName) {
        return mPreferences.getBoolean(flagName, false);
    }

}
