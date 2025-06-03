package com.example.musicmanagement.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
   private long userId;
   private String username;
   private String password;
   private LocalDateTime createdAt;
}