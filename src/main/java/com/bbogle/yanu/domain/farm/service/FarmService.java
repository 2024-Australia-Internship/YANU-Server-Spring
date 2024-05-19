package com.bbogle.yanu.domain.farm.service;

import com.bbogle.yanu.domain.farm.dto.RegisterFarmRequestDto;
import com.bbogle.yanu.domain.farm.domain.FarmEntity;
import com.bbogle.yanu.domain.user.domain.UserEntity;
import com.bbogle.yanu.global.exception.FarmDuplicateException;
import com.bbogle.yanu.global.exception.FarmNotFoundException;
import com.bbogle.yanu.global.exception.SessionNotFoundException;
import com.bbogle.yanu.global.exception.error.ErrorCode;
import com.bbogle.yanu.domain.farm.repository.FarmRepository;
import com.bbogle.yanu.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FarmService {
    private final FarmRepository farmRepository;
    private final UserRepository userRepository;

    public void registerFarm(RegisterFarmRequestDto request){
        /*HttpSession session = httpRequest.getSession(false);

        if(session == null){
            throw new SessionNotFoundException("session not found", ErrorCode.SESSION_NOTFOUND);
        }

        Long id = (Long) session.getAttribute("userId");*/

        Long id = request.getUserId().getId();

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
