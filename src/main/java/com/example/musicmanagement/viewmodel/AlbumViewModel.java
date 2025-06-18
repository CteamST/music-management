package com.example.musicmanagement.viewmodel;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class AlbumViewModel {
    private long albumId;
    private String title;
    private String artist;
    private String comment;
    private int priority;
    private LocalDate releaseDate;
    private int musicCount;
    private List<Progress> progress;
}