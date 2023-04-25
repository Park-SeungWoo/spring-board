package com.example.cloneboard.dao.boards;

import com.example.cloneboard.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    BoardEntity findByPostIdAndNickname(Long id, String nickname);

    List<BoardEntity> findByNickname(String nickname);

    void deleteByNickname(String nickname);

    Page<BoardEntity> findAllByOrderById(Pageable pageable);

    Page<BoardEntity> findAllByNicknameOrderById(Pageable pageable, String nickname);

    // naming convention

}
