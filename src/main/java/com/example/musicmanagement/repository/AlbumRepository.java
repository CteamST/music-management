package com.example.musicmanagement.repository;

import com.example.musicmanagement.mapper.AlbumMapper;
import com.example.musicmanagement.entity.Album;
import org.springframework.stereotype.Repository;
import com.example.musicmanagement.viewmodel.AlbumViewModel;
import java.util.List;

@Repository
public class AlbumRepository {
  private final AlbumMapper albumMapper;

  public AlbumRepository(AlbumMapper albumMapper) {
    this.albumMapper = albumMapper;
  }

  public List<Album> getAllAlbums() {
    return albumMapper.selectAllAlbums();
  }

  public void insertAlbum(Album album) {
    albumMapper.insertAlbum(album);
  }

  public Album getAlbumById(long albumId) {
    return albumMapper.selectAlbumById(albumId);
  }

  public void deleteAlbum(long albumId) {
    albumMapper.deleteAlbumById(albumId);
  }

  public void updateAlbum(Album album) {
    albumMapper.updateAlbum(album);
  }

  public List<AlbumViewModel> getAllAlbumsWithMusicCount() {
    return albumMapper.selectAllAlbumsWithMusicCount();
  }
}