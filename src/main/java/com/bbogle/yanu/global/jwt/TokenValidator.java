package com.bbogle.yanu.global.jwt;

import com.bbogle.yanu.global.exception.TokenNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenValidator {
    private final TokenProvider tokenProvider;

    public String  validateToken(HttpServletRequest httpServletRequest){
        String token = tokenProvider.resolveToken(httpServletRequest);
        if (token == null || !tokenProvider.validToken(token)) {
            throw new TokenNotFoundException("Invalid token", ErrorCode.TOKEN_NOTFOUND);
        }
        return token;
    }
}
