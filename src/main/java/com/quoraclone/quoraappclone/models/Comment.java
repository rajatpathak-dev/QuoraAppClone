package com.quoraclone.quoraappclone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Comment extends BaseModel {
    private String commentContent;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @OneToMany(mappedBy = "parentCommentId")
    private Set<Comment> replies;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentCommentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "comment_likes",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likedBy;
}
