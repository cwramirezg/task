package com.gmail.cwramirezg.task.data.pojos;

import com.gmail.cwramirezg.task.BuildConfig;

public class Configuracion {
    private boolean batch;
    private String servidor;

    public Configuracion() {
        this.batch = false;
        setServidor(BuildConfig.BASE_URL_IP);
    }

    public boolean isBatch() {
        return batch;
    }

    public void setBatch(boolean batch) {
        this.batch = batch;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        servidor = servidor.trim();
        int len = servidor.length();
        String lastChar = servidor.substring(len - 1);
        if (lastChar.equals("/")) servidor = servidor.substring(0, len - 1);
        this.servidor = servidor;
    }

}
