package com.example.musicmanagement.viewmodel;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalTime;

@Data
public class MusicViewModel {
    private long musicId;
    private String title;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime duration;
    private boolean isFavorite;
}
