package com.example.cloneboard.dto.pages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
public class PageInfo {
    private int page;
    private int size;
    private int contentSize;
    private long totalElements;
    private long totalPages;

}
