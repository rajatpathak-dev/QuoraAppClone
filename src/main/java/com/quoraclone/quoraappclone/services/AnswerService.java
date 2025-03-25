package com.quoraclone.quoraappclone.services;

import com.quoraclone.quoraappclone.dtos.AnswerDto;
import com.quoraclone.quoraappclone.exceptions.AnswerNotFoundException;
import com.quoraclone.quoraappclone.exceptions.QuestionNotFoundException;
import com.quoraclone.quoraappclone.exceptions.UserNotFoundException;
import com.quoraclone.quoraappclone.models.Answer;
import com.quoraclone.quoraappclone.models.Question;
import com.quoraclone.quoraappclone.models.User;
import com.quoraclone.quoraappclone.repositories.AnswerRepository;
import com.quoraclone.quoraappclone.repositories.QuestionRepository;
import com.quoraclone.quoraappclone.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private AnswerRepository answerRepository;
    private QuestionRepository questionRepository;
    private UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository,QuestionRepository questionRepository,UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public List<Answer> getAllAnswerByQuestionId(Long questionId,int page,int limit){
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if(questionOptional.isEmpty()){
            throw  new QuestionNotFoundException("question with id "+questionId+" not found");
        }
        return answerRepository.findByQuestionId(questionId, PageRequest.of(page,limit)).getContent();
    }

    public void deleteAnswer(Long answerId){
        Optional<Answer> answerOptional = answerRepository.findById(answerId);
        if(answerOptional.isEmpty()){
            throw  new AnswerNotFoundException("answer with id "+answerId+"not found");
        }
        answerRepository.deleteById(answerId);
        }

        public Answer createAnswer(AnswerDto answerDto){
          Optional<User> userOptional = userRepository.findById(answerDto.getUserId());
          if(userOptional.isEmpty()){
              throw  new UserNotFoundException("user with id "+answerDto.getUserId()+" not found");
          }

          Optional<Question> questionOptional = questionRepository.findById(answerDto.getQuestionId());
          if(questionOptional.isEmpty()){
              throw new QuestionNotFoundException("question with id "+answerDto.getQuestionId()+" not found");
          }
          Answer answer = new Answer();
          answer.setAnswerContent(answerDto.getContent());
          answer.setUser(userOptional.get());
          answer.setQuestion(questionOptional.get());
          return answerRepository.save(answer);

        }

}
