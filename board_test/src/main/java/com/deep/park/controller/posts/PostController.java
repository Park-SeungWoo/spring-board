package com.deep.park.controller.posts;

import com.deep.park.dto.Post;
import com.deep.park.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController  // rest api server로 동작할 수 있도록 함. 기존엔 controller, responsebody annotation을 이용해 mvc의 view를 리턴하는게 아닌 데이터 자체를 return했음.
@RequestMapping("/members/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public List<Post> showAllPosts(){
        return postService.showAllPosts();
    }

    @GetMapping("/{id}")
    public List<Post> showOnesPosts(@PathVariable("id") Long creatorId){
        return postService.showOnesPosts(creatorId);
    }

    @PostMapping
    public void postOneContent(@RequestHeader Map<String, String> header){
        Post post = new Post();
        post.setCreatorId(Long.parseLong(header.get("creator_id")));  // key값을 모두 소문자로 받아옴
        post.setTitle(header.get("title"));
        post.setContent(header.get("content"));

        postService.post(post);
    }
}
