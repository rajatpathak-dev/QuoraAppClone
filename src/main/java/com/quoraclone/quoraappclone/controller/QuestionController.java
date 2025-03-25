package com.quoraclone.quoraappclone.controller;

import com.quoraclone.quoraappclone.dtos.QuestionDto;
import com.quoraclone.quoraappclone.models.Tag;
import com.quoraclone.quoraappclone.services.QuestionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllQuestion(@RequestParam Integer page,@RequestParam Integer limit){
        return new ResponseEntity<>(questionService.getAllQuestons(page,limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id){
        return new ResponseEntity<>(questionService.getQuestionById(id),HttpStatus.OK);
    }

    @GetMapping("/tags")
    public ResponseEntity<?> getAllQuestionsInTags(@RequestBody Set<Tag> tagSet , @RequestParam Integer page,@RequestParam Integer limit){
        return new ResponseEntity<>(questionService.findAllQuestionsInTags(tagSet,page,limit),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return new ResponseEntity<>("successfully deleted",HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDto questionDto){
        return new ResponseEntity<>(questionService.createQuestion(questionDto),HttpStatus.OK);
    }
}
