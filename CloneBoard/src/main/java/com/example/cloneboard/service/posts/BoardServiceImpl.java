package com.example.cloneboard.service.posts;

import com.example.cloneboard.dao.boards.BoardRepository;
import com.example.cloneboard.dao.users.UserRepository;
import com.example.cloneboard.dto.boards.BoardSaveRequestDto;
import com.example.cloneboard.dto.boards.BoardUpdateRequestDto;
import com.example.cloneboard.dto.pages.PaginationResponse;
import com.example.cloneboard.entity.BoardEntity;
import com.example.auth.dto.users.UserAuthorizedDto;
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

    @Transactional
    @Override
    public ResponseEntity<String> post(BoardSaveRequestDto boardSaveRequestDto, UserAuthorizedDto authorizedUser){  // save a post
        try {
            if (authorizedUser.getNickname().equals(boardSaveRequestDto.getNickname())) {
                UserEntity userEntity = userRepository.findByNickname(boardSaveRequestDto.getNickname());  // 유저별로 포스트 id 다르게 하기 위해 postSequence 꺼내오기 (전체 id는 따로 있음)
                boardSaveRequestDto.setPostId(userEntity.getPostSequence());  // user의 postSequence를 postId로 함
                userEntity.addPostSequence(); // postSequence 사용했으니 하나 플러스해주기
                boardRepository.save(boardSaveRequestDto.toEntity());  // post저장
                return ResponseEntity.ok("성공: 성공적으로 저장했습니다!");
            } else
                return ResponseEntity.ok("실패: 요청의 닉네임이 user정보와 다릅니다.");
        } catch (Exception e) {
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

    @Transactional
    @Override
    public ResponseEntity<String> delete(Long id, String nickname, UserAuthorizedDto authorizedUser){  // delete a post by nickname and post id
        try {
            if (authorizedUser.getNickname().equals(nickname)) {
                boardRepository.delete(boardRepository.findByPostIdAndNickname(id, nickname));
                return ResponseEntity.ok("성공: 성공적으로 삭제했습니다.");
            } else
                return ResponseEntity.ok("실패: 작성자가 아닙니다.");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @Transactional
    @Override
    public ResponseEntity<String> update(Long id, String nickname, BoardUpdateRequestDto boardUpdateRequestDto, UserAuthorizedDto authorizedUser) {
        /*
        Transactional annotation을 사용하면 dirty checking을 해 findBy~로 데이터를 조회한 시점에 snapshot을 남겨(영속성 context에 저장) transaction이 끝나는 시점에 데이터를 비교해 변경사항이 있으면 데이터를 변경해줌.
        기본적으로 dirty checking을 통해 생성된 update query는 모든 필드를 변경, entity에 @DynamicUpdate를 하면 변경된것만 변경해줌
         */

        /*
        1차 캐싱 후 찾아서 있으면 db까지 안가고 바꾸고 마지막에 결과만 db로 감(영속성 컨텍스트)
         */
        try {
            if (authorizedUser.getNickname().equals(nickname)) {
                BoardEntity post = boardRepository.findByPostIdAndNickname(id, nickname);
                post.changeTitleAndContent(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
                return ResponseEntity.ok("성공: 성공적으로 업데이트 했습니다.");
            } else
                return ResponseEntity.ok("실패: 작성자가 아닙니다.");  // 작성자가 아닐 때 return null
        } catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
