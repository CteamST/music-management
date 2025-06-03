package com.example.musicmanagement.controller;

import com.example.musicmanagement.security.CustomUserDetails;
import com.example.musicmanagement.service.FavoriteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public String addFavorite(@RequestParam long musicId,
            @RequestParam long albumId,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            RedirectAttributes redirectAttributes) {
        favoriteService.insertFavorite(userDetails.getUserId(), albumId, musicId);
        redirectAttributes.addFlashAttribute("message", "お気に入りに追加しました");
        return "redirect:/albums/" + albumId;
    }

    @PostMapping("/delete")
    public String deleteFavorite(@RequestParam long musicId,
            @RequestParam long albumId,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            RedirectAttributes redirectAttributes) {
        favoriteService.deleteFavorite(userDetails.getUserId(), musicId);
        redirectAttributes.addFlashAttribute("message", "お気に入りを解除しました");
        return "redirect:/albums/" + albumId;
    }
}