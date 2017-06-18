package ru.kpfu.itis.javaLab.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Safin Ramil on 11.06.17
 * RamilSafNab1996@gmail.com
 */
public class ErrorResponseBody implements Serializable {

    private String message;

    private int code;

    public ErrorResponseBody() {

    }

    public ErrorResponseBody(String message) {
        this.message = message;
    }

    public ErrorResponseBody(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("code")
    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
