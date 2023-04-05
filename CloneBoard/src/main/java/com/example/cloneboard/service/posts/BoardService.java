package com.example.cloneboard.service.posts;

import com.example.cloneboard.dto.boards.BoardResponseDto;
import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.dto.boards.BoardUpdateRequestDto;

import java.util.List;

public interface BoardService {
    public Long post(BoardSaveRequestDto boardSaveRequestDto);
    public List<BoardResponseDto> findOnes(String nickname);
    public List<BoardResponseDto> findAll();
    public void delete(Long id);
    public BoardResponseDto update(Long id, String nickname, BoardUpdateRequestDto boardUpdateRequestDto);
}
