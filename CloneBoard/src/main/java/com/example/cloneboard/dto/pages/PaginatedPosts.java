package com.example.cloneboard.dto.pages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class PaginatedPosts<T> {
    private T data;
    private PageInfo pageInfo;
}
