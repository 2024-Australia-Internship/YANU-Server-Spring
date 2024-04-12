package com.bbogle.yanu.service;

import com.bbogle.yanu.dto.farm.RegisterFarmRequestDto;
import com.bbogle.yanu.entity.FarmEntity;
import com.bbogle.yanu.entity.UserEntity;
import com.bbogle.yanu.exception.FarmDuplicateException;
import com.bbogle.yanu.exception.FarmNotFoundException;
import com.bbogle.yanu.exception.SessionNotFoundException;
import com.bbogle.yanu.exception.error.ErrorCode;
import com.bbogle.yanu.repository.FarmRepository;
import com.bbogle.yanu.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FarmService {
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;

    public void registerFarm(RegisterFarmRequestDto request, HttpServletRequest httpRequest){
        HttpSession session = httpRequest.getSession(false);

        if(session == null){
            throw new SessionNotFoundException("session not found", ErrorCode.SESSION_NOTFOUND);
        }

        Long id = (Long) session.getAttribute("userId");

        if(farmRepository.existsByUserId(id)){
            throw new FarmDuplicateException("farm duplicated", ErrorCode.FARM_DUPKICATION);
        }

        UserEntity user = userRepository.findUserById(id);
        request.setUserId(user);
        farmRepository.save(request.toEntity());
    }

    public FarmEntity farmFindById(HttpServletRequest httpRequest){
        HttpSession session = httpRequest.getSession();

        if(session == null){
            throw new SessionNotFoundException("session not found", ErrorCode.SESSION_NOTFOUND);
        }

        Long id = (Long) session.getAttribute("userId");

        return farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException("farm not found", ErrorCode.FARM_NOTFOUND));
    }

    public FarmEntity framFindByUser (Long id){
        return farmRepository.findByUserId(id);
    }

}
