package com.example.cloneboard.service.posts;

import com.example.cloneboard.dao.boards.BoardRepository;
import com.example.cloneboard.dto.boards.BoardResponseDto;
import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.dto.boards.BoardUpdateRequestDto;
import com.example.cloneboard.entity.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Transactional
    @Override
    public Long post(BoardSaveRequestDto boardSaveRequestDto){  // save a post
        return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
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
    public void delete(Long id){  // delete a post by id
        boardRepository.deleteById(id);
    }

    @Transactional
    @Override
    public BoardResponseDto update(Long id, String nickname, BoardUpdateRequestDto boardUpdateRequestDto) {
        /*
        Transactional annotation을 사용하면 dirty checking을 해 findBy~로 데이터를 조회한 시점에 snapshot을 남겨 transaction이 끝나는 시점에 데이터를 비교해 변경사항이 있으면 데이터를 변경해줌.
        기본적으로 dirty checking을 통해 생성된 update query는 모든 필드를 변경, entity에 @DynamicUpdate를 하면 변경된것만 변경해줌
         */
        BoardEntity post = boardRepository.findByIdAndNickname(id, nickname);
        post.changeTitleAndContent(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return new BoardResponseDto(post);
    }
}
