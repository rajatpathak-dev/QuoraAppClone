package com.quoraclone.quoraappclone.services;

import com.quoraclone.quoraappclone.dtos.CommentDto;
import com.quoraclone.quoraappclone.exceptions.AnswerNotFoundException;
import com.quoraclone.quoraappclone.exceptions.CommentNotFoundException;
import com.quoraclone.quoraappclone.exceptions.UserNotFoundException;
import com.quoraclone.quoraappclone.models.Comment;
import com.quoraclone.quoraappclone.models.User;
import com.quoraclone.quoraappclone.repositories.AnswerRepository;
import com.quoraclone.quoraappclone.repositories.CommentRepository;
import com.quoraclone.quoraappclone.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private final AnswerRepository answerRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository,
                          AnswerRepository answerRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }

    public List<Comment> getAllCommentsByAnswerId(Long answerId,int page,int size){
        return commentRepository.findByAnswerId(answerId, PageRequest.of(page,size)).getContent();
    }

    public List<Comment> getAllCommentsByCommentId(Long commentId,int page,int size){
        return commentRepository.findByParentCommentId(commentId, PageRequest.of(page,size)).getContent();
    }

    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }

    public Comment addComment(CommentDto commentDto){
        Optional<User> userOptional = userRepository.findById(commentDto.getUserId());
        if(userOptional.isEmpty()){
            throw  new UserNotFoundException("user with id "+commentDto.getUserId()+" not found");
        }
        Comment comment = new Comment();
        comment.setCommentContent(commentDto.getContent());
        comment.setUser(userOptional.get());
        if(commentDto.getAnswerId()!=null){
            if(answerRepository.findById(commentDto.getAnswerId()).isEmpty()){
                throw new AnswerNotFoundException("answer with id "+commentDto.getAnswerId()+" not exist");
            }
            comment.setAnswer(answerRepository.findById(commentDto.getAnswerId()).get());
        }else{
            if(commentRepository.findById(commentDto.getParentCommentId()).isEmpty()){
                throw new CommentNotFoundException("Comment with id "+commentDto.getParentCommentId()+" not exist");
            }
            comment.setParentCommentId(commentRepository.findById(commentDto.getParentCommentId()).get());
        }
        return commentRepository.save(comment);
    }
}
