package com.quoraclone.quoraappclone.dtos;

import lombok.Data;

@Data
public class CommentDto {
    private String content;
    private long userId;
    private Long answerId;
    private Long parentCommentId;
}
