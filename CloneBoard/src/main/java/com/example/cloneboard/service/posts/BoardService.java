package com.example.cloneboard.service.posts;

import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.dto.boards.BoardUpdateRequestDto;
import com.example.cloneboard.dto.pages.PaginationResponse;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    public ResponseEntity<String> post(BoardSaveRequestDto boardSaveRequestDto);
    public ResponseEntity<String> delete(Long id, String nickname);
    public ResponseEntity<String> update(Long id, String nickname, BoardUpdateRequestDto boardUpdateRequestDto);
    public PaginationResponse findAllPagination(int page, int size);
    public PaginationResponse findOnesPagination(int page, int size, String nickname);
}
