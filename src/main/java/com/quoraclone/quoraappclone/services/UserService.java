package com.quoraclone.quoraappclone.services;

import com.quoraclone.quoraappclone.dtos.UserDto;
import com.quoraclone.quoraappclone.exceptions.TagNotFoundException;
import com.quoraclone.quoraappclone.exceptions.UserNotFoundException;
import com.quoraclone.quoraappclone.models.Tag;
import com.quoraclone.quoraappclone.models.User;
import com.quoraclone.quoraappclone.repositories.TagRepository;
import com.quoraclone.quoraappclone.repositories.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class UserService {
    private UserRepository userRepository;
    private TagRepository tagRepository;

    public UserService(UserRepository userRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("user with id "+userId+" not found");
        }
        return userOptional.get();
    }

    public User addUser(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
       return userRepository.save(user);
    }

    public User deleteUser(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("user with id "+userId+" not found");
        }
        userRepository.deleteById(userId);
        return userOptional.get();
    }

    public void followTag(Long userId,Long tagId){
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found"));
        Tag tag =  tagRepository.findById(tagId).orElseThrow(()->new TagNotFoundException("tag not found"));
        user.getFollowedTags().add(tag);
        userRepository.save(user);
    }
}
