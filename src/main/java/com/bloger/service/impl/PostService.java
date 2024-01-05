package com.bloger.service.impl;

import com.bloger.payload.PostDto;


public interface PostService {

   public void createPost(PostDto postDto);


 public   void deletePost(long id);

    PostDto getAll(long postId, PostDto postDto);
}
