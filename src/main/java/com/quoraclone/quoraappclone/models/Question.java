package com.quoraclone.quoraappclone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Question extends BaseModel{

    private String questionContent;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    @ManyToMany
    @JoinTable(name = "question_tags",
               joinColumns = @JoinColumn(name = "question_id"),
               inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
