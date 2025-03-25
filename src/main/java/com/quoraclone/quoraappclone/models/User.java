package com.quoraclone.quoraappclone.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User extends BaseModel{
    private String username;
    private String password;

    @ManyToMany()
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> followedTags;

    @OneToMany(mappedBy = "user")
    private Set<Question> questions;

    @OneToMany(mappedBy = "user")
    private Set<Answer> answers;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;
}
