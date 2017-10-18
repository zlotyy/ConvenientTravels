package com.mvc.helpers;


import java.util.ArrayList;
import java.util.List;

public class ServiceResult<T> {
    private boolean isValid;
    public List<String> errors;
    private List<String> messages;
    private T data;

    public ServiceResult(){
        errors = new ArrayList<String>();
    }

    public boolean isValid() {
        return errors.size() == 0;      //jesli brak bledow to result isValid
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
