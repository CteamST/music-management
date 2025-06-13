package com.example.musicmanagement.service;

import com.example.musicmanagement.entity.Music;
import com.example.musicmanagement.repository.MusicRepository;
import org.springframework.stereotype.Service;
import com.example.musicmanagement.form.MusicForm;
import com.example.musicmanagement.viewmodel.MusicViewModel;
import com.example.musicmanagement.exception.AlbumNotFoundException;
import com.example.musicmanagement.entity.Album;
import com.example.musicmanagement.repository.AlbumRepository;
import org.springframework.transaction.annotation.Transactional;
import com.example.musicmanagement.exception.MusicNotFoundException;
import java.util.List;

@Service
public class MusicService {
    private final MusicRepository musicRepository;
    private final AlbumRepository albumRepository;

    public MusicService(MusicRepository musicRepository, AlbumRepository albumRepository) {
        this.musicRepository = musicRepository;
        this.albumRepository = albumRepository;
    }

    public List<Music> getMusicsByAlbumId(long albumId) {
        return musicRepository.getMusicsByAlbumId(albumId);
    }

    @Transactional
    public void createMusic(MusicForm musicForm) {
        Album existingAlbum = albumRepository.getAlbumById(musicForm.getAlbumId());
        if (existingAlbum == null) {
            throw new AlbumNotFoundException("Album not found");
        }
        Music music = new Music();
        music.setTitle(musicForm.getTitle());
        music.setDaydate(musicForm.getDaydate());
        music.setProgress(musicForm.getProgress());
        music.setAlbumId(musicForm.getAlbumId());
        musicRepository.insertMusic(music);
    }

    public void deleteMusic(long musicId) {
        musicRepository.deleteMusicById(musicId);
    }

    public Music getMusicById(long musicId) {
        return musicRepository.selectMusicById(musicId);
    }

    @Transactional
    public void updateMusic(long musicId, Music music) {
        Music existingMusic = getMusicById(musicId);
        if (existingMusic == null) {
            throw new MusicNotFoundException("Music not found", music.getAlbumId());
        }

        if (musicId != music.getMusicId()) {
            throw new MusicNotFoundException("Music ID does not match", music.getAlbumId());
        }
        musicRepository.updateMusic(music);
    }

    public List<MusicViewModel> selectMusicsWithFavorite(long albumId, long userId) {
        return musicRepository.selectMusicsWithFavorite(albumId, userId);
    }

}
