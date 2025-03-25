package com.quoraclone.quoraappclone.repositories;

import com.quoraclone.quoraappclone.models.Answer;
import com.quoraclone.quoraappclone.models.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {


    Page<Answer> findByQuestionId (Long QuestionId, Pageable pageable);
}
