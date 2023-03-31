package com.example.cloneboard.service.posts;

import com.example.cloneboard.dao.boards.BoardRepository;
import com.example.cloneboard.dto.boards.BoardResponseDto;
import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.entity.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long post(BoardSaveRequestDto boardSaveRequestDto){  // save a post
        return boardRepository.save(boardSaveRequestDto.toEntity()).getId();
    }

    public List<BoardResponseDto> findOnes(String nickname){  // find one's posts by nickname
        List<BoardEntity> posts = boardRepository.findByNickname(nickname);
        return posts.stream().map(BoardResponseDto::new).collect(Collectors.toList());  // parse entity list to dto list
    }

    public List<BoardResponseDto> findAll(){  // find all posts
        List<BoardEntity> posts = boardRepository.findAll();
        return posts.stream().map(BoardResponseDto::new).collect(Collectors.toList());  // parse entity list to dto list
    }

    @Transactional
    public void delete(Long id){  // delete a post by id
        boardRepository.deleteById(id);
    }
}
