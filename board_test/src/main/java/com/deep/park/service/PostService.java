package com.deep.park.service;

import com.deep.park.dao.PostRepository;
import com.deep.park.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public void post(Post post) {  // save post
        postRepository.save(post);
    }

    public List<Post> showAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> showOnesPosts(Long creatorId) {
        return postRepository.findByCreatorId(creatorId);  // custom findBy~
    }
}
