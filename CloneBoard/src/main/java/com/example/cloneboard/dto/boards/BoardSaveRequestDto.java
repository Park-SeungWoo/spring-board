package com.example.cloneboard.dto.boards;

import com.example.cloneboard.entity.BoardEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
public class BoardSaveRequestDto {
    private Long postId;
    private String title;
    private String content;
    private String nickname;

    public void setPostId(Long id){
        this.postId = id;
    }

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .title(this.title)
                .content(this.content)
                .nickname(this.nickname)
                .postId(this.postId)
                .build();
    }
}
