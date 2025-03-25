package com.quoraclone.quoraappclone.controller;

import com.quoraclone.quoraappclone.dtos.UserDto;
import com.quoraclone.quoraappclone.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long userId){
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.addUser(userDto),HttpStatus.OK);
    }

    @PostMapping("/{userId}/follwedTag/{tagID}")
    public ResponseEntity<?> addFollwedTag(@PathVariable Long userId,@PathVariable Long tagID){
        userService.followTag(userId,tagID);
        return new ResponseEntity<>("Tag followed succesfully",HttpStatus.OK);
    }

}
