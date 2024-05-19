package com.bbogle.yanu.domain.favorite.service;

import com.bbogle.yanu.domain.favorite.dto.DeleteHeartRequestDto;
import com.bbogle.yanu.domain.favorite.dto.FindHeartRequestDto;
import com.bbogle.yanu.domain.favorite.dto.RegisterHeartRequestDto;
import com.bbogle.yanu.domain.favorite.domain.FavoriteEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.global.exception.HeartDuplicateException;
import com.bbogle.yanu.global.exception.HeartNotFoundException;
import com.bbogle.yanu.global.exception.SessionNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.domain.favorite.repository.FavoriteRepository;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;

    public void registerHeart(RegisterHeartRequestDto request){
        /*HttpSession session = httpRequest.getSession(false);

        if(session == null){
            throw new SessionNotFoundException("session not found", ErrorCode.SESSION_NOTFOUND);
        }

        Long userId = (Long) session.getAttribute("userId");*/
        Long userId =  request.getUserId().getId();
        Long productId = request.getProductId().getId();
        String type = request.getType();
        boolean exists = favoriteRepository.existsByUserIdAndProductIdAndType(userId, productId, type);

        if(exists){
            throw new HeartDuplicateException("heart duplicated", ErrorCode.HEART_DUPLICATION);
        }

        UserEntity user = userRepository.findUserById(userId);
        request.setUserId(user);
        favoriteRepository.save(request.toEntity());
    }

    @Transactional
    public void deleteHeart(DeleteHeartRequestDto request){
        /*HttpSession session = httpRequest.getSession(false);

        if(session == null){
            throw new SessionNotFoundException("session not found", ErrorCode.SESSION_NOTFOUND);
        }

        Long userId = (Long) session.getAttribute("userId");*/
        Long userId = request.getUserId().getId();
        Long productId = request.getProductId().getId();
        String type = request.getType();
        boolean exists = favoriteRepository.existsByUserIdAndProductIdAndType(userId, productId, type);

        if(!exists){
            throw new HeartNotFoundException("heart notfound", ErrorCode.HEART_NOTFOUND);
        }

        favoriteRepository.deleteByUserIdAndProductIdAndType(userId, productId, type);
    }

    public List<FavoriteEntity> findHeart(String type, FindHeartRequestDto request){
        /*HttpSession session = httpRequest.getSession(false);

        if(session == null){
            throw new SessionNotFoundException("session not found", ErrorCode.SESSION_NOTFOUND);
        }

        Long id = (Long) session.getAttribute("userId");*/

        Long id = request.getUserId().getId();

        List<FavoriteEntity> hearts = favoriteRepository.findAllByTypeAndUserId(type, id);

        if(hearts.isEmpty())
            throw new HeartNotFoundException("heart notfound", ErrorCode.HEART_NOTFOUND);

        return hearts;
    }
}
