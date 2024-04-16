package com.bbogle.yanu.exception;

import com.bbogle.yanu.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class CartNotFoundException extends RuntimeException{
    ErrorCode errorCode;

    public CartNotFoundException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
