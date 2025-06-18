package com.example.musicmanagement.controller;

import com.example.musicmanagement.entity.Album;
import com.example.musicmanagement.entity.Music;
import com.example.musicmanagement.service.AlbumService;
import com.example.musicmanagement.service.MusicService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.musicmanagement.form.AlbumForm;
import com.example.musicmanagement.form.MusicForm;
import com.example.musicmanagement.viewmodel.AlbumViewModel;
import com.example.musicmanagement.security.CustomUserDetails;
import com.example.musicmanagement.viewmodel.MusicViewModel;
import com.example.musicmanagement.viewmodel.Progress;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumController {
  private final AlbumService albumService;
  private final MusicService musicService;

  public AlbumController(AlbumService albumService, MusicService musicService) {
    this.albumService = albumService;
    this.musicService = musicService;

  }

  @GetMapping
  public String albums(Model model,@RequestParam(required = false, defaultValue = "1") int page) {
    // List<Album> albums = albumService.getAllAlbums();
    List<AlbumViewModel> albums = albumService.getAllAlbumsWithMusicCount();
    
    for (int i = 0; i < albums.size(); i++){
      // albumsから1件データを取り出す
      AlbumViewModel date = albums.get(i);
      // 取り出したデータからalbumIdを取り出す
      long albumId = date.getAlbumId();
      // albumIdを使ってDBからProgressのリストを取り出す
      List<Progress> music = musicService.progressCount(albumId);
      // Progressのリストを取り出したalbumsのデータに代入する
      date.setProgress(music);
    }
    
    int limitNum = 10;
    int maxCount = albums.size();
    int maxPages = (maxCount -1) / limitNum + 1;
    int count = maxCount - limitNum * (page -1);
    List<AlbumViewModel> currenAlbums = new ArrayList<>();
    
    if(count >= limitNum){
      currenAlbums = new ArrayList<>(albums.subList((page -1) * limitNum, limitNum));
    } else if(count > 0){
      currenAlbums = new ArrayList<>(albums.subList((page -1) * limitNum, (page -1) * limitNum + count));
    }

    model.addAttribute("albums", currenAlbums);
    model.addAttribute("pages", maxPages);
    model.addAttribute("currentPage", page);

    return "album/album-list";
  }

  @GetMapping("/new")
  public String albumForm(Model model) {
    AlbumForm albumForm = new AlbumForm();
    model.addAttribute("albumForm", albumForm);
    return "album/album-form";
  }

  @PostMapping("/new")
  public String createAlbum(AlbumForm albumForm) { // , Model model) {
    albumService.createAlbum(albumForm);
    // List<Album> albums = albumService.getAllAlbums();
    // model.addAttribute("albums", albums);
    // return "album/album-list";
    return "redirect:/albums";
  }

  @GetMapping("/{albumId}")
  public String album(@PathVariable long albumId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
    Album album = albumService.getAlbumById(albumId);
    //List<Music> musics = musicService.getMusicsByAlbumId(albumId);
    List<MusicViewModel> musics = musicService.selectMusicsWithFavorite(albumId, userDetails.getUserId());
    model.addAttribute("album", album);
    model.addAttribute("musics", musics);
    return "album/album-detail";
  }

  @PostMapping("/{albumId}/delete")
  public String deleteAlbum(@PathVariable long albumId) {
    musicService.deleteMusicByAlbumId(albumId);
    albumService.deleteAlbum(albumId);
    return "redirect:/albums";
  }

  @GetMapping("/{albumId}/edit")
  public String editAlbum(@PathVariable long albumId, Model model) {
    Album album = albumService.getAlbumById(albumId);
    model.addAttribute("album", album);
    return "album/album-edit";
  }

  @PostMapping("/{albumId}/edit")
  public String updateAlbum(@PathVariable long albumId, Album album) {
    albumService.updateAlbum(albumId, album);
    return "redirect:/albums";
  }

  @GetMapping("/{albumId}/musics/new")
  public String createMusicForm(@PathVariable long albumId, Model model) {
    MusicForm musicForm = new MusicForm();
    musicForm.setAlbumId(albumId);
    model.addAttribute("musicForm", musicForm);
    return "music/music-form";
  }

  @PostMapping("/{albumId}/musics/new")
  public String createMusic(@PathVariable long albumId, MusicForm musicForm) {
    musicService.createMusic(musicForm);
    return "redirect:/albums/" + albumId;
  }

  @PostMapping("/{albumId}/musics/{musicId}/delete")
  public String deleteMusic(@PathVariable long albumId, @PathVariable long musicId) {
    musicService.deleteMusic(musicId);
    return "redirect:/albums/" + albumId;
  }

  @GetMapping("/{albumId}/musics/{musicId}/edit")
  public String editMusic(@PathVariable long albumId, @PathVariable long musicId, Model model) {
    Music music = musicService.getMusicById(musicId);
    model.addAttribute("music", music);
    return "music/music-edit";
  }

  @PostMapping("/{albumId}/musics/{musicId}/edit")
  public String updateMusic(@PathVariable long albumId, @PathVariable long musicId, Music music) {
    musicService.updateMusic(musicId, music);
    return "redirect:/albums/" + albumId;
  }
}
