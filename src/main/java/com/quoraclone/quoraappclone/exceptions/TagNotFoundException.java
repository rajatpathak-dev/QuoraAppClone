package com.quoraclone.quoraappclone.exceptions;

public class TagNotFoundException extends  RuntimeException{
    public TagNotFoundException(String message) {
        super(message);
    }
}
