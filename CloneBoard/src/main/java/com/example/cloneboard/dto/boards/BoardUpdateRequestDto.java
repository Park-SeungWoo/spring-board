package com.example.cloneboard.dto.boards;

import com.example.cloneboard.entity.BoardEntity;
import lombok.Getter;

@Getter
public class BoardUpdateRequestDto {
    private String title;
    private String content;

    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
