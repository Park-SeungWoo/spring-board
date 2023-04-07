package com.example.cloneboard.service.users;

import com.example.cloneboard.dao.users.UserRepository;
import com.example.cloneboard.dto.users.UserAuthorizedDto;
import com.example.cloneboard.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class UserAuthorizationServiceImpl implements UserAuthorizationService{
    private final String secret = "Authorized";
    private final UserRepository userRepository;

    @Override
    public String createToken(String email) {
        return email + "%" + secret;
    }

    @Override
    public UserAuthorizedDto validate(String userToken) {
        try {
            String[] userDatas = userToken.split("%");
            UserEntity user = userRepository.findByNickname(userDatas[0]);
            if (userDatas[1].equals("Authorized") && user != null)
                return new UserAuthorizedDto(user);
            else
                return null;
        } catch (Exception e){
            return null;
        }
    }
}
