package com.bbogle.yanu.global.exception;

import com.bbogle.yanu.global.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordNotFoundException extends RuntimeException{
    private ErrorCode errorCode;

    public PasswordNotFoundException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
