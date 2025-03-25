package com.quoraclone.quoraappclone.dtos;

import lombok.Data;

@Data
public class QuestionDto {
    private String content;
    private String tag;
    private Long userId;
}
