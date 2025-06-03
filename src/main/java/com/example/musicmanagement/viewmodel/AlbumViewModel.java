package com.example.musicmanagement.viewmodel;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AlbumViewModel {
    private long albumId;
    private String title;
    private String artist;
    private LocalDate releaseDate;
    private int musicCount;
}