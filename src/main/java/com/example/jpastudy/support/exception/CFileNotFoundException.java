package com.example.jpastudy.support.exception;

public class CFileNotFoundException extends CBoardNotFoundException{
    private static final long serialVersionUID = 1L;

    public CFileNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CFileNotFoundException(String msg) {
        super(msg);
    }

    public CFileNotFoundException() {
        super();
    }
}
