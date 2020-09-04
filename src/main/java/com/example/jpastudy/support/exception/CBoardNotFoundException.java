package com.example.jpastudy.support.exception;

public class CBoardNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CBoardNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CBoardNotFoundException(String msg) {
        super(msg);
    }

    public CBoardNotFoundException() {
        super();
    }
}
