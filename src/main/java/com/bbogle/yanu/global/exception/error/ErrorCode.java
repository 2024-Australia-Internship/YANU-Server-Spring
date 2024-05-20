package com.bbogle.yanu.global.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INTER_SERVER_ERROR(500, "SERVER-ERROR-500", "내부 서버 오류가 발생했습니다."),
    EMAIL_DUPLICATION(400, "USER-ERROR-400", "이미 가입된 이메일 입니다."),
    PHONENUMBER_DUPLICATION(400, "USER-ERROR-400", "이미 가입된 전화번호 입니다"),
    EMAIL_NOTFOUND(404, "USER-ERROR-404", "등록된 이메일이 아닙니다"),
    PASSWORD_NOTFOUND(404, "USER-ERROR-404", "비밀번호가 일치하지 않습니다"),
    USER_NOTFOUND(404,"USER-ERROR-404", "해당 사용자를 찾을 수 없습니다"),
    PRODUCT_NOTFOUND(404, "PRODUCT-ERROR-404", "해당 상품을 찾을 수 없습니다"),
    HEART_DUPLICATION(400, "HEART-ERROR-400", "이미 등록된 하트 입니다"),
    HEART_NOTFOUND(404,"HEART-ERROR-404", "해당 하트를 찾을 수 없습니다"),
    SESSION_NOTFOUND(401, "SESSION-ERROR-401", "세션이 조회되지 않습니다"),
    FARM_DUPKICATION(400, "FARM-ERROR-400", "이미 농장을 등록한 회원입니다."),
    FARM_NOTFOUND(404, "FARM-ERROR-404", "해당 농장을 찾을 수 없습니다"),
    CART_DUPLICATION(400, "CART-ERROR-400", "이미 등록된 장바구니 상품 입니다."),
    CART_NOTFOUND(404, "CART-ERROR-404", "해당 장바구니 내역을 찾을 수 없습니다."),
    TOKEN_NOTFOUND(401, "TOKEN-ERROR-401", "해당 토큰이 조회되지 않습니다");

    private int status;
    private String errorCode;
    private String errorMessage;
}
