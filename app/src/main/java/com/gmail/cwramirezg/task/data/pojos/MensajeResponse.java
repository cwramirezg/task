package com.gmail.cwramirezg.task.data.pojos;

import com.google.gson.annotations.SerializedName;

public class MensajeResponse {

    @SerializedName("exit_code")
    private int cod;
    @SerializedName("message")
    private String msg;

    public MensajeResponse(int cod, String msg) {
        this.cod = cod;
        this.msg = msg;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
