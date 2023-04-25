package com.example.cloneboard.utils;

import com.example.cloneboard.dao.boards.BoardRepository;
import com.example.cloneboard.dto.boards.BoardResponseDto;
import com.example.cloneboard.dto.pages.PageInfo;
import com.example.cloneboard.dto.pages.PaginatedPosts;
import com.example.cloneboard.entity.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PaginationMethods {
    private final BoardRepository boardRepository;
    public PaginatedPosts getPaginatedPost(int page, int size){
        // page info
        PageRequest pageRequest = PageRequest.of(page - 1, size);  // page는 0번부터 존재
        Page<BoardEntity> paginatedData = boardRepository.findAllByOrderById(pageRequest);
        PageInfo pageInfo = PageInfo.builder()
                .page(page)
                .size(size)
                .contentSize(paginatedData.getNumberOfElements())
                .totalElements(paginatedData.getTotalElements())
                .totalPages(paginatedData.getTotalPages())
                .build();
        // post datas
        List<BoardResponseDto> posts = paginatedData.getContent().stream().map(BoardResponseDto::new).collect(Collectors.toList());   // parse entity list to dto list

        return PaginatedPosts.builder()
                .pageInfo(pageInfo)
                .data(posts)
                .build();
    }

    // overloading
    public PaginatedPosts getPaginatedPost(int page, int size, String nickname){
        // page info
        PageRequest pageRequest = PageRequest.of(page - 1, size);  // page는 0번부터 존재
        Page<BoardEntity> paginatedData = boardRepository.findAllByNicknameOrderById(pageRequest, nickname);
        PageInfo pageInfo = PageInfo.builder()
                .page(page)
                .size(size)
                .contentSize(paginatedData.getNumberOfElements())
                .totalElements(paginatedData.getTotalElements())
                .totalPages(paginatedData.getTotalPages())
                .build();
        // post datas
        List<BoardResponseDto> posts = paginatedData.getContent().stream().map(BoardResponseDto::new).collect(Collectors.toList());   // parse entity list to dto list

        return PaginatedPosts.builder()
                .pageInfo(pageInfo)
                .data(posts)
                .build();
    }
}
