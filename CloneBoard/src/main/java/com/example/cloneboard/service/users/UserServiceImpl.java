package com.example.cloneboard.service.users;

import com.example.cloneboard.dao.users.UserRepository;
import com.example.cloneboard.dto.users.UserJoinRequestDto;
import com.example.cloneboard.dto.users.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Primary
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public Long join(UserJoinRequestDto userJoinRequestDto){  // save a user
        return userRepository.save(userJoinRequestDto.toEntity()).getId();
    }

    @Override
    public UserResponseDto findOne(String email){  // find a user by email
        return userRepository.findByEmail(email).stream().map(UserResponseDto::new).collect(Collectors.toList()).get(0);
        // parse 1 length of UserEntity list(found user) to UserResponseDto
    }

    @Transactional
    @Override
    public void delete(String email){  // delete a user by email
        userRepository.deleteByEmail(email);
    }
}
