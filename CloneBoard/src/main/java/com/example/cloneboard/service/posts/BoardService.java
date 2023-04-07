package com.example.cloneboard.service.posts;

import com.example.cloneboard.dto.boards.BoardResponseDto;
import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.dto.boards.BoardUpdateRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {
    public Long post(BoardSaveRequestDto boardSaveRequestDto, String token);
    public List<BoardResponseDto> findOnes(String nickname);
    public List<BoardResponseDto> findAll();
    public ResponseEntity<String> delete(Long id, String nickname, String token);
    public BoardResponseDto update(Long id, String nickname, BoardUpdateRequestDto boardUpdateRequestDto, String token);
}
