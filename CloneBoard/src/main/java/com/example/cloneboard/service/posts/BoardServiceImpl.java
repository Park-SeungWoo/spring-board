package com.example.cloneboard.service.posts;

import com.example.cloneboard.dao.boards.BoardRepository;
import com.example.cloneboard.dto.boards.BoardResponseDto;
import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.dto.boards.BoardUpdateRequestDto;
import com.example.cloneboard.dto.users.UserAuthorizedDto;
import com.example.cloneboard.entity.BoardEntity;
import com.example.cloneboard.entity.UserEntity;
import com.example.cloneboard.service.users.UserAuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final UserAuthorizationService userAuthorizationService;

    @Transactional
    @Override
    public Long post(BoardSaveRequestDto boardSaveRequestDto, String token){  // save a post
        UserAuthorizedDto user = userAuthorizationService.validate(token);
        if(user != null && user.getNickname().equals(boardSaveRequestDto.getNickname()))
            return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
        else
            return -1L;
    }

    @Override
    public List<BoardResponseDto> findOnes(String nickname){  // find one's posts by nickname
        List<BoardEntity> posts = boardRepository.findByNickname(nickname);
        return posts.stream().map(BoardResponseDto::new).collect(Collectors.toList());  // parse entity list to dto list
    }

    @Override
    public List<BoardResponseDto> findAll(){  // find all posts
        List<BoardEntity> posts = boardRepository.findAll();
        return posts.stream().map(BoardResponseDto::new).collect(Collectors.toList());  // parse entity list to dto list
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(Long id, String nickname, String token){  // delete a post by nickname and id
        UserAuthorizedDto user = userAuthorizationService.validate(token);
        if(user != null && user.getNickname().equals(nickname)) {
            boardRepository.deleteById(id);
            return ResponseEntity.ok("deleted");
        } else
            return ResponseEntity.ok("Authorization failed!");


        // entity 찾아서 아래처럼 해도 됨.
//        boardRepository.delete(entity);
    }

    @Transactional
    @Override
    public BoardResponseDto update(Long id, String nickname, BoardUpdateRequestDto boardUpdateRequestDto, String token) {
        /*
        Transactional annotation을 사용하면 dirty checking을 해 findBy~로 데이터를 조회한 시점에 snapshot을 남겨(영속성 context에 저장) transaction이 끝나는 시점에 데이터를 비교해 변경사항이 있으면 데이터를 변경해줌.
        기본적으로 dirty checking을 통해 생성된 update query는 모든 필드를 변경, entity에 @DynamicUpdate를 하면 변경된것만 변경해줌
         */

        /*
        1차 캐싱 후 찾아서 있으면 db까지 안가고 바꾸고 마지막에 결과만 db로 감(영속성 컨텍스트)
         */
        UserAuthorizedDto user = userAuthorizationService.validate(token);
        if(user != null && user.getNickname().equals(nickname)) {
            BoardEntity post = boardRepository.findByIdAndNickname(id, nickname);
            post.changeTitleAndContent(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
            return new BoardResponseDto(post);
        } else
            return null;
    }
}
