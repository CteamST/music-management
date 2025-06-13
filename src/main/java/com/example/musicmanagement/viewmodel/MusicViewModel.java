package com.example.musicmanagement.viewmodel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MusicViewModel {
    private long musicId;
    private String title;
    private int progress;
    private LocalDate daydate;
    private boolean isFavorite;
}
