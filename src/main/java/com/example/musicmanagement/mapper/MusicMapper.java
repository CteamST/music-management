package com.example.musicmanagement.mapper;

import com.example.musicmanagement.entity.Music;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import com.example.musicmanagement.viewmodel.MusicViewModel;
import com.example.musicmanagement.viewmodel.Progress;

import java.util.List;

@Mapper
public interface MusicMapper {
    @Select("SELECT * FROM musics WHERE album_id = #{albumId}")
    List<Music> selectMusicsById(long album_id);

    @Insert("INSERT INTO musics (title, daydate, progress, album_id) VALUES (#{title}, #{daydate}, #{progress}, #{albumId})")
    @Options(useGeneratedKeys = true, keyProperty = "musicId")
    void insertMusic(Music music);

    @Delete("DELETE FROM musics WHERE music_id = #{musicId}")
    void deleteMusicById(long musicId);

    @Delete("DELETE FROM musics WHERE album_id = #{albumId}")
    void deleteMusicByAlbumId(long albumId);

    @Update("UPDATE musics SET title = #{title}, daydate = #{daydate}, progress = #{progress} WHERE music_id = #{musicId}")
    void updateMusic(Music music);

    @Select("SELECT * FROM musics WHERE music_id = #{musicId}")
    Music selectMusicById(long musicId);

    @Select("""
            SELECT
                musics.music_id,
                title,
                daydate,
                progress,
                favorites.user_id IS NOT NULL AS is_favorite
            FROM musics
            LEFT JOIN favorites ON musics.music_id = favorites.music_id AND favorites.user_id = #{userId}
            WHERE album_id = #{albumId}
            ORDER BY daydate ASC;
            """)
            List<MusicViewModel> selectMusicsWithFavorite(long albumId, long userId);
    @Select("SELECT progress, COUNT(progress) AS count FROM musics WHERE album_id = #{albumId} GROUP BY progress")
    List<Progress> progressCount(long albumId);

}