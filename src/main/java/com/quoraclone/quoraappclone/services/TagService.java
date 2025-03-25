package com.quoraclone.quoraappclone.services;

import com.quoraclone.quoraappclone.dtos.TagDto;
import com.quoraclone.quoraappclone.exceptions.TagNotFoundException;
import com.quoraclone.quoraappclone.models.Tag;
import com.quoraclone.quoraappclone.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    public Tag getTagById(Long tagId){
        Optional<Tag> tagOptional = tagRepository.findById(tagId);
        if(tagOptional.isEmpty()){
            throw  new TagNotFoundException("tag with id "+tagId+" not found");
        }
        return tagOptional.get();
    }

    public Tag createTag(TagDto tagDto){
        Tag tag = new Tag();
        tag.setTagName(tagDto.getTagName());
        return tagRepository.save(tag);

    }

    public void deleteTag(Long tagId){
        Optional<Tag> tagOptional = tagRepository.findById(tagId);
        if(tagOptional.isEmpty()){
            throw  new TagNotFoundException("tag with id "+tagId+" not found");
        }
        tagRepository.deleteById(tagId);
    }
}
