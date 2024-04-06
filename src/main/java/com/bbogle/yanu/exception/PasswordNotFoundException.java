package com.bbogle.yanu.exception;

import com.bbogle.yanu.exception.error.ErrorCode;

public class PasswordNotFoundException extends RuntimeException{
    private ErrorCode errorCode;

    public PasswordNotFoundException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
