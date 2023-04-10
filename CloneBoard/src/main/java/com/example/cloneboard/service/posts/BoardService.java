package com.example.cloneboard.service.posts;

import com.example.auth.dto.users.UserAuthorizedDto;
import com.example.cloneboard.dto.boards.BoardResponseDto;
import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.dto.boards.BoardUpdateRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {
    public ResponseEntity<String> post(BoardSaveRequestDto boardSaveRequestDto, UserAuthorizedDto user);
    public List<BoardResponseDto> findOnes(String nickname);
    public List<BoardResponseDto> findAll();
    public ResponseEntity<String> delete(Long id, String nickname, UserAuthorizedDto user);
    public ResponseEntity<String> update(Long id, String nickname, BoardUpdateRequestDto boardUpdateRequestDto, UserAuthorizedDto user);
}
