package com.bbogle.yanu.exception;

import com.bbogle.yanu.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    private ErrorCode errorCode;

    public UserNotFoundException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
