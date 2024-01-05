package com.bloger.payload;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {


    private long id;
    private  String content;

    private  String description;

    private String reply;
    private String repost;
}
