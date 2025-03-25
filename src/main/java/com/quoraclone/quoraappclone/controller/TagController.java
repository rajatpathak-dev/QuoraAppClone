package com.quoraclone.quoraappclone.controller;

import com.quoraclone.quoraappclone.dtos.TagDto;
import com.quoraclone.quoraappclone.services.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tag")
public class TagController {
    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping()
    public ResponseEntity<?> getAlltags(){
        return new ResponseEntity<>(tagService.getAllTags(), HttpStatus.OK);
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<?> getTagById(@PathVariable Long tagId){
        return new ResponseEntity<>(tagService.getTagById(tagId),HttpStatus.OK);
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<?> deleteTagById(@PathVariable Long tagId){
        tagService.deleteTag(tagId);
        return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addTag(@RequestBody TagDto tagDto){
        return new ResponseEntity<>(tagService.createTag(tagDto),HttpStatus.OK);
    }



}
