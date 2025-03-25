package com.quoraclone.quoraappclone.controller;

import com.quoraclone.quoraappclone.dtos.AnswerDto;
import com.quoraclone.quoraappclone.services.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {
    private AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<?> getAllAnswersWithQuestionId(@PathVariable Long id,@RequestParam Integer page,@RequestParam Integer limit){
        return new ResponseEntity<>(answerService.getAllAnswerByQuestionId(id,page,limit), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswerById(@PathVariable Long id){
        answerService.deleteAnswer(id);
        return new ResponseEntity<>("Answer Deleted Successfully",HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addAnswer(@RequestBody AnswerDto answerDto){
        return new ResponseEntity<>(answerService.createAnswer(answerDto),HttpStatus.OK);
    }
}
