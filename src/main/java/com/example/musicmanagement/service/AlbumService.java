package com.example.musicmanagement.service;

import com.example.musicmanagement.repository.AlbumRepository;
import com.example.musicmanagement.entity.Album;
import org.springframework.stereotype.Service;
import com.example.musicmanagement.form.AlbumForm;
import com.example.musicmanagement.viewmodel.AlbumViewModel;
import com.example.musicmanagement.exception.AlbumNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AlbumService {
  private final AlbumRepository albumRepository;

  public AlbumService(AlbumRepository albumRepository) {
    this.albumRepository = albumRepository;
  }

  public List<Album> getAllAlbums() {
    return albumRepository.getAllAlbums();
  }

  public void createAlbum(AlbumForm albumForm) {
    Album album = new Album();
    album.setTitle(albumForm.getTitle());
    album.setArtist(albumForm.getArtist());
    album.setReleaseDate(albumForm.getReleaseDate());
    album.setComment(albumForm.getComment());
    albumRepository.insertAlbum(album);
  }

  public Album getAlbumById(long albumId) {
    return albumRepository.getAlbumById(albumId);
  }

  public void deleteAlbum(long albumId) {
    albumRepository.deleteAlbum(albumId);
  }

  @Transactional
  public void updateAlbum(long albumId, Album album) {
    Album existingAlbum = getAlbumById(albumId);
    if (existingAlbum == null) {
      throw new AlbumNotFoundException("Album not found");
    }
    if (albumId != album.getAlbumId()) {
      throw new  AlbumNotFoundException("Album ID does not match");
    }
    albumRepository.updateAlbum(album);
  }

  public List<AlbumViewModel> getAllAlbumsWithMusicCount() {
    return albumRepository.getAllAlbumsWithMusicCount();
  }
}