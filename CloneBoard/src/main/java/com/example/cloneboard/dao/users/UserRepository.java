package com.example.cloneboard.dao.users;

import com.example.cloneboard.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    void deleteByEmail(String email);
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByNickname(String nickname);
    boolean existsByNickname(String nickname);
}
