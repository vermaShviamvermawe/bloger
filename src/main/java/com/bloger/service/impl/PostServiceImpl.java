package com.bloger.service.impl;

import com.bloger.entity.Post;
import com.bloger.payload.PostDto;
import com.bloger.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{
@Autowired
private PostRepository repo;

    @Override
    public void createPost(PostDto postDto) {
        Post post=new Post();
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setRepost(postDto.getRepost());
        post.setReply(postDto.getReply());
repo.save(post);
    }

    @Override
    public void deletePost(long id) {
        repo.deleteById(id);
    }

    @Override
    public PostDto getAll(long postId, PostDto postDto) {
Post post=repo.findById(postId).orElseThrow(
        ()-> new RuntimeException("data not found")
);
post.setReply(postDto.getReply());
post.setRepost(postDto.getRepost());
post.setContent(postDto.getContent());
post.setDescription(postDto.getContent());
repo.save(post);
PostDto dto=new PostDto();
        dto.setContent(post.getContent());
        dto.setReply(post.getReply());
        dto.setRepost(post.getRepost());
        dto.setContent(post.getContent());
return dto;
    }
}
