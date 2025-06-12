package com.example.musicmanagement.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Music {
    private long musicId;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate daydate;
    private int progress;
    private long albumId;
    private LocalDateTime createdAt;
}