package com.example.musicmanagement.repository;

import com.example.musicmanagement.mapper.FavoriteMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FavoriteRepository {
    private final FavoriteMapper favoriteMapper;

    public FavoriteRepository(FavoriteMapper favoriteMapper) {
        this.favoriteMapper = favoriteMapper;
    }

    public void insertFavorite(long userId, long musicId) {
        favoriteMapper.insertFavorite(userId, musicId);
    }

    public void deleteFavorite(long userId, long musicId) {
        favoriteMapper.deleteFavorite(userId, musicId);
    }
}