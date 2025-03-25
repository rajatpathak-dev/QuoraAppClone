package com.quoraclone.quoraappclone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Tag extends BaseModel{
    private String tagName;

    @ManyToMany(mappedBy = "followedTags")
    private Set<User> followers;

    @ManyToMany(mappedBy = "tags")
    private Set<Question> questions;
}
