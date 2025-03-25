package com.quoraclone.quoraappclone.repositories;

import com.quoraclone.quoraappclone.models.Question;
import com.quoraclone.quoraappclone.models.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query("select q from Question q JOIN q.tags t WHERE t IN :tagSet")
    Page<Question> findByTagId(Set<Tag> tagSet, Pageable pageable);
}
