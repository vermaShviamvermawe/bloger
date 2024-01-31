package com.bloger.controller;

import com.bloger.payload.PostDto;
import com.bloger.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
@Autowired
private PostService postService;
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto){
        postService.createPost(postDto);
   return new ResponseEntity<>("Your data is Saved" , HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted",HttpStatus.OK);
    }


    @PutMapping
    public PostDto getAll(
            @RequestBody PostDto postDto,
            @RequestParam("post_id") long post_id){
      return postService.getAll(post_id,postDto);
    }


}
