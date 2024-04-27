package com.bbogle.yanu.global.exception;

import com.bbogle.yanu.global.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    private ErrorCode errorCode;

    public UserNotFoundException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
