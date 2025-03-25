package com.quoraclone.quoraappclone.services;

import com.quoraclone.quoraappclone.dtos.QuestionDto;
import com.quoraclone.quoraappclone.exceptions.QuestionNotFoundException;
import com.quoraclone.quoraappclone.exceptions.UserNotFoundException;
import com.quoraclone.quoraappclone.models.Question;
import com.quoraclone.quoraappclone.models.Tag;
import com.quoraclone.quoraappclone.models.User;
import com.quoraclone.quoraappclone.repositories.QuestionRepository;
import com.quoraclone.quoraappclone.repositories.TagRepository;
import com.quoraclone.quoraappclone.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public List<Question> getAllQuestons(int offset,int limit){
        return questionRepository.findAll(PageRequest.of(offset,limit)).getContent();
    }

    public Question getQuestionById(Long questionId){
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if(questionOptional.isEmpty()){
            throw new QuestionNotFoundException("Question with id "+questionId+" not found");
        }
        return questionOptional.get();
    }

    public void deleteQuestion(Long questionId){
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if(questionOptional.isEmpty()){
            throw new QuestionNotFoundException("Question with id "+questionId+" not found");
        }
        questionRepository.deleteById(questionId);
    }

    public Question createQuestion(QuestionDto questionDto){
        Question question = new Question();
        question.setQuestionContent(questionDto.getContent());

         Optional<Tag> tagOptional  = tagRepository.findByTagName(questionDto.getTag());
         if(tagOptional.isEmpty()){
             Tag tag = new Tag();
             tag.setTagName(questionDto.getTag());
             question.getTags().add(tagRepository.save(tag));
         }else{
             question.getTags().add(tagOptional.get());
         }

         Optional<User> userOptional = userRepository.findById(questionDto.getUserId());
         if(userOptional.isEmpty()){
             throw  new UserNotFoundException("user with id "+questionDto.getUserId()+" not exist");
         }
         question.setUser(userOptional.get());
         return questionRepository.save(question);


    }

    public List<Question> findAllQuestionsInTags(Set<Tag> tagSet,Integer page,Integer limit){
        return questionRepository.findByTagId(tagSet, PageRequest.of(page,limit)).getContent();
    }
}
