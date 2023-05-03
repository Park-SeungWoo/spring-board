package com.example.cloneboard.controller.boards;

import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.dto.boards.BoardUpdateRequestDto;
import com.example.cloneboard.service.posts.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> post(@RequestBody BoardSaveRequestDto boardSaveRequestDto
    ) {
        return boardService.post(boardSaveRequestDto);
    }

    /* find ones
        total 3 methods (overload)
     */
    @GetMapping("/{nickname}")
    public ResponseEntity findOnes(@PathVariable String nickname) {
        return new ResponseEntity(
                boardService.findOnesPagination(1, 10, nickname),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/{nickname}", params = {"page"})
    public ResponseEntity findOnes(
            @PathVariable String nickname,
            @RequestParam int page
    ) {
        return new ResponseEntity(
                boardService.findOnesPagination(page, 10, nickname),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/{nickname}", params = {"page", "size"})
    public ResponseEntity findOnes(
            @PathVariable String nickname,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity(
                boardService.findOnesPagination(page, size, nickname),
                HttpStatus.OK
        );
    }

    /* find all
        total 3 methods (overload)
     */
    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity(
                boardService.findAllPagination(1, 10),
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"page"})
    public ResponseEntity findAll(
            @RequestParam int page
    ) {
        return new ResponseEntity(
                boardService.findAllPagination(page, 10),
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity findAll(
            @RequestParam int page,
            @RequestParam int size
    ) {
        return new ResponseEntity(
                boardService.findAllPagination(page, size),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{nickname}/{id}")  // patch는 부분변경, put은 전체데이터 갈아끼우기(patch는 멱등성을 보장해주진 않음)
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @PathVariable String nickname,
                                         @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        return boardService.update(id, nickname, boardUpdateRequestDto);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestHeader(value = "id") Long id,
                                         @RequestHeader(value = "nickname") String nickname) {
        return boardService.delete(id, nickname);
    }

}
