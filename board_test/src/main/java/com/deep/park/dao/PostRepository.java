package com.deep.park.dao;

import com.deep.park.dto.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatorId(Long id);  // findby~~로 뒤의 문자열을 바탕으로 쿼리 생성, 현재는 (creator_id가 id인걸 찾아줌)
}
