package com.example.musicmanagement.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Music {
    private long musicId;
    private String title;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime duration;
    private long albumId;
    private LocalDateTime createdAt;
}