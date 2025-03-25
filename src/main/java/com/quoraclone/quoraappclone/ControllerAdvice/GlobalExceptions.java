package com.quoraclone.quoraappclone.ControllerAdvice;

import com.quoraclone.quoraappclone.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<?> tagNotFoundExceptionHandler(TagNotFoundException tagNotFoundException){
        return new ResponseEntity<>(tagNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<?> questionNotFoundExceptionHandler(QuestionNotFoundException questionNotFoundException){
        return new ResponseEntity<>(questionNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> commentNotFoundExceptionHandler(CommentNotFoundException commentNotFoundException){
        return new ResponseEntity<>(commentNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AnswerNotFoundException.class)
    public ResponseEntity<?> answerNotFoundExceptionHandler(AnswerNotFoundException answerNotFoundException){
        return new ResponseEntity<>(answerNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }


}
