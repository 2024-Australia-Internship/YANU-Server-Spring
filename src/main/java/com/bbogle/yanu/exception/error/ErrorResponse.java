package com.bbogle.yanu.exception.error;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResponse {
    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getErrorMessage();
        this.code = errorCode.getErrorCode();
    }
}
