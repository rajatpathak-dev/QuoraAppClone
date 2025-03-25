package com.quoraclone.quoraappclone.dtos;

import lombok.Data;

@Data
public class AnswerDto {
    private String content;
    private Long questionId;
    private Long userId;

}
