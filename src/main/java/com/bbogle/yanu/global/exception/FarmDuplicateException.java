package com.bbogle.yanu.global.exception;

import com.bbogle.yanu.global.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class FarmDuplicateException extends RuntimeException{
    private ErrorCode errorCode;

    public FarmDuplicateException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
