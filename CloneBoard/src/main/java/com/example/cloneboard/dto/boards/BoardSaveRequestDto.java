package com.example.cloneboard.dto.boards;

import com.example.cloneboard.entity.BoardEntity;
import lombok.Getter;

@Getter
public class BoardSaveRequestDto {
    private String title;
    private String content;
    private String nickname;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .title(this.title)
                .content(this.content)
                .nickname(this.nickname)
                .build();
    }
}
