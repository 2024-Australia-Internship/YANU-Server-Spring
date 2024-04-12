package com.bbogle.yanu.exception;

import com.bbogle.yanu.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class SessionNotFoundException extends RuntimeException{
    private ErrorCode errorCode;

    public SessionNotFoundException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
