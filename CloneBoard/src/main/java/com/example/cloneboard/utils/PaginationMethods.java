package com.example.cloneboard.utils;

import com.example.cloneboard.dao.boards.BoardRepository;
import com.example.cloneboard.dto.boards.BoardResponseDto;
import com.example.cloneboard.dto.pages.PageInfo;
import com.example.cloneboard.dto.pages.PaginationResponse;
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

    private PaginationResponse pageToPaginationResponse(Page<BoardEntity> pageData, int page, int size){
        PageInfo pageInfo = PageInfo.builder()
                .page(page)
                .size(size)
                .contentSize(pageData.getNumberOfElements())
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .build();
        // post datas
        List<BoardResponseDto> posts = pageData.getContent().stream().map(BoardResponseDto::new).collect(Collectors.toList());   // parse entity list to dto list

        return PaginationResponse.builder()
                .pageInfo(pageInfo)
                .data(posts)
                .build();
    }
    public PaginationResponse getPaginatedPost(int page, int size){
        // page info
        PageRequest pageRequest = PageRequest.of(page - 1, size);  // page는 0번부터 존재
        Page<BoardEntity> paginatedData = boardRepository.findAllByOrderById(pageRequest);
        return pageToPaginationResponse(paginatedData, page, size);
    }

    // overloading
    public PaginationResponse getPaginatedPost(int page, int size, String nickname){
        // page info
        PageRequest pageRequest = PageRequest.of(page - 1, size);  // page는 0번부터 존재
        Page<BoardEntity> paginatedData = boardRepository.findAllByNicknameOrderById(pageRequest, nickname);
        return pageToPaginationResponse(paginatedData, page, size);
    }
}
