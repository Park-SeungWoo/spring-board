package com.example.cloneboard.controller.boards;

import com.example.cloneboard.dto.boards.BoardResponseDto;
import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.service.posts.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @PostMapping
    public Long post(@RequestBody BoardSaveRequestDto boardSaveRequestDto){
        return boardService.post(boardSaveRequestDto);
    }

    @GetMapping("/{nickname}")
    public List<BoardResponseDto> findOnes(@PathVariable("nickname") String nickname){
        return boardService.findOnes(nickname);
    }

    @GetMapping
    public List<BoardResponseDto> findAll(){
        return boardService.findAll();
    }

    // update title, content by nickname

    @DeleteMapping
    public void delete(@RequestHeader(value = "id") Long id){
        boardService.delete(id);
    }

}
