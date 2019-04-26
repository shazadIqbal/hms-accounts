package com.hs.accounts.Commons;

import java.util.List;

public class RestTemplateResponseDTO<T> {
    private String code;
    private String message;
    private T body;
    private List<T> bodyList;


    public RestTemplateResponseDTO() {
    }

    public RestTemplateResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestTemplateResponseDTO(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }
    public RestTemplateResponseDTO(String code, String message, List<T> bodyList) {
        this.code = code;
        this.message = message;
        this.bodyList = bodyList;
    }

    public List<T> getBodyList() {
        return bodyList;
    }

    public void setBodyList(List<T> bodyList) {
        this.bodyList = bodyList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
