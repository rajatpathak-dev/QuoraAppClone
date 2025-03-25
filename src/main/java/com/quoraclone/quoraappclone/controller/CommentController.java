package com.quoraclone.quoraappclone.controller;

import com.quoraclone.quoraappclone.dtos.CommentDto;
import com.quoraclone.quoraappclone.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ap1/v1/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity<?> getAllCommentsByAnswerId(@PathVariable Long id, @RequestParam Integer page,@RequestParam Integer limit){
        return new ResponseEntity<>(commentService.getAllCommentsByAnswerId(id,page,limit), HttpStatus.OK);
    }

    @GetMapping("/answer/comment/{id}")
    public ResponseEntity<?> getAllCommentsByCommentId(@PathVariable Long id, @RequestParam Integer page,@RequestParam Integer limit){
        return new ResponseEntity<>(commentService.getAllCommentsByCommentId(id,page,limit), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommentById(@PathVariable Long id){
        commentService.deleteComment(id);
        return new ResponseEntity<>("Comment Deleted Successfully",HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addComment(@RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.addComment(commentDto),HttpStatus.OK);
    }

}
