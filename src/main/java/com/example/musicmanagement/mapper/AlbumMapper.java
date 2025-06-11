
package com.example.musicmanagement.mapper;

import com.example.musicmanagement.entity.Album;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import com.example.musicmanagement.viewmodel.AlbumViewModel;
import java.util.List;

@Mapper
public interface AlbumMapper {
  @Select("SELECT * FROM albums")
  List<Album> selectAllAlbums();

  @Insert("INSERT INTO albums (title, artist, release_date, comment) VALUES (#{title}, #{artist}, #{releaseDate}, #{comment})")
  @Options(useGeneratedKeys = true, keyProperty = "albumId")
  void insertAlbum(Album album);

  @Select("SELECT * FROM albums WHERE album_id = #{albumId}")
  Album selectAlbumById(long albumId);

  @Delete("DELETE FROM albums WHERE album_id = #{albumId}")
  void deleteAlbumById(long albumId);

  @Update("UPDATE albums SET title = #{title}, artist = #{artist}, release_date = #{releaseDate}, comment = #{comment} WHERE album_id = #{albumId}")
  void updateAlbum(Album album);

  @Select("""
      SELECT albums.album_id, albums.title, artist, release_date, comment, count(musics.music_id) AS music_count
      FROM albums
      LEFT OUTER JOIN musics ON albums.album_id = musics.album_id
      GROUP BY albums.album_id, albums.title, artist, release_date, comment
      """)
  public List<AlbumViewModel> selectAllAlbumsWithMusicCount();
}