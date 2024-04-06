package com.bbogle.yanu.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INTER_SERVER_ERROR(500, "SERVER-ERROR-500", "내부 서버 오류가 발생했습니다.");

    private int status;
    private String errorCode;
    private String errorMessage;
}
