package com.mvc.helpers;

public class ResultError extends Result {

    public ResultError(){
        this.setSuccess(false);
    }

    public ResultError(String error){
        this.setSuccess(false);
        this.setError(error);
    }
}
