package com.example.cloneboard.controller.boards;

import com.example.auth.dto.users.UserAuthorizedDto;
import com.example.cloneboard.dto.boards.BoardResponseDto;
import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.dto.boards.BoardUpdateRequestDto;
import com.example.cloneboard.service.posts.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Restfull 하게 patch, delete등도 사용함. 실무에선 get, post 이외의 것들은 잘 안쓴다고 함
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @PostMapping
//    @ModelAttribute  // param으로 들어온것들 Dto로 받을 수 있게 해줌
    public ResponseEntity<String> post(@RequestBody BoardSaveRequestDto boardSaveRequestDto,
                                       @RequestAttribute("userStatus") UserAuthorizedDto userAuthorizedDto
                                       ){
        return boardService.post(boardSaveRequestDto, userAuthorizedDto);
    }

    @GetMapping("/{nickname}")
    public List<BoardResponseDto> findOnes(@PathVariable("nickname") String nickname){
        return boardService.findOnes(nickname);
    }

    @GetMapping
    public List<BoardResponseDto> findAll(){
        return boardService.findAll();
    }

    @PatchMapping("/{nickname}/{id}")  // patch는 부분변경, put은 전체데이터 갈아끼우기(patch는 멱등성을 보장해주진 않음)
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @PathVariable String nickname,
                                         @RequestBody BoardUpdateRequestDto boardUpdateRequestDto,
                                         @RequestAttribute("userStatus") UserAuthorizedDto userAuthorizedDto
                                         ){
        return boardService.update(id, nickname, boardUpdateRequestDto, userAuthorizedDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestHeader(value = "id") Long id,
                                         @RequestHeader(value = "nickname") String nickname,
                                         @RequestAttribute("userStatus") UserAuthorizedDto userAuthorizedDto
                                         ){
        return boardService.delete(id, nickname, userAuthorizedDto);
    }

}
