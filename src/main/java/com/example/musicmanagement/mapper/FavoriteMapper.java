package com.example.musicmanagement.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteMapper {
    @Insert("INSERT INTO favorites (user_id, music_id) VALUES (#{userId}, #{musicId})")
    void insertFavorite(long userId, long musicId);

    @Delete("DELETE FROM favorites WHERE user_id = #{userId} AND music_id = #{musicId}")
    void deleteFavorite(long userId, long musicId);

}