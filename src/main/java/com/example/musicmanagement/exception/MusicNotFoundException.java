package com.example.musicmanagement.exception;

import lombok.Getter;

@Getter
public class MusicNotFoundException extends RuntimeException {
    private final long albumId;

    public MusicNotFoundException(String message, long albumId) {
        super(message);
        this.albumId = albumId;
    }
}