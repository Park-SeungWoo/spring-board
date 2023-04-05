package com.example.cloneboard.dao.boards;

import com.example.cloneboard.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByNickname(String nickname);
    BoardEntity findByIdAndNickname(Long id, String nickname);
}
