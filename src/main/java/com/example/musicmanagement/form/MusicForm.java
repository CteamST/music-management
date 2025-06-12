package com.example.musicmanagement.form;

import lombok.Data;

import java.time.LocalDate;


@Data
public class MusicForm {
    private String title;
    private LocalDate daydate;
    private int progress ;
    private long albumId;
}
