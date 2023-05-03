package com.example.cloneboard.service.posts;

import com.example.cloneboard.dao.boards.BoardRepository;
import com.example.cloneboard.dao.users.UserRepository;
import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.dto.boards.BoardUpdateRequestDto;
import com.example.cloneboard.dto.pages.PaginationResponse;
import com.example.cloneboard.entity.BoardEntity;
import com.example.cloneboard.entity.UserEntity;
import com.example.cloneboard.utils.PaginationMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final PaginationMethods paginationMethods;

    /*
    토큰 정보와 게시글 작성, 업데이트, 삭제 요청 내부의 정보 확인 즉 토큰 속 정보와, 실제 토큰 사용자를 체크하는 것은 추후에 진행하기
     */

    @Transactional
    @Override
    public ResponseEntity<String> post(BoardSaveRequestDto boardSaveRequestDto){  // save a post
        try {
            UserEntity userEntity = userRepository.findByNickname(boardSaveRequestDto.getNickname());  // 유저별로 포스트 id 다르게 하기 위해 postSequence 꺼내오기 (전체 id는 따로 있음)
            boardSaveRequestDto.setPostId(userEntity.getPostSequence());  // user의 postSequence를 postId로 함
            userEntity.addPostSequence(); // postSequence 사용했으니 하나 플러스해주기
            boardRepository.save(boardSaveRequestDto.toEntity());  // post저장
            return ResponseEntity.ok("성공: 성공적으로 저장했습니다!");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> delete(Long id, String nickname){  // delete a post by nickname and post id
        try {
            boardRepository.delete(boardRepository.findByPostIdAndNickname(id, nickname));
            return ResponseEntity.ok("성공: 성공적으로 삭제했습니다.");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> update(Long id, String nickname, BoardUpdateRequestDto boardUpdateRequestDto) {
        try {
            BoardEntity post = boardRepository.findByPostIdAndNickname(id, nickname);
            post.changeTitleAndContent(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
            return ResponseEntity.ok("성공: 성공적으로 업데이트 했습니다.");
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Override
    public PaginationResponse findOnesPagination(int page, int size, String nickname){
        return paginationMethods.getPaginatedPost(page, size, nickname);
    }

    @Override
    public PaginationResponse findAllPagination(int page, int size){
        return paginationMethods.getPaginatedPost(page, size);
    }
}
