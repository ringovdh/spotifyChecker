package be.yorian.spotifychecker.controller;

import be.yorian.spotifychecker.service.CurrentTrackService;
import be.yorian.spotifychecker.service.TopArtistsService;
import be.yorian.spotifychecker.service.TopTracksService;
import be.yorian.spotifychecker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {

    private final UserService userService;
    private final CurrentTrackService currentTrackService;
    private final TopTracksService topTracksService;
    private final TopArtistsService topArtistsService;

    @Autowired
    public DashboardController(UserService userService,
                               CurrentTrackService currentTrackService,
                               TopTracksService topTracksService,
                               TopArtistsService topArtistsService)
    {
        this.userService = userService;
        this.currentTrackService = currentTrackService;
        this.topTracksService = topTracksService;
        this.topArtistsService = topArtistsService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, final HttpSession session)
    {

        String token = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", token);
        model.addAttribute("user", userService.getUser(token));
        model.addAttribute("currentTrack", currentTrackService.getCurrentTrack(token));
        model.addAttribute("topTracks", topTracksService.getDashboardTopTracks(token));
        model.addAttribute("topArtists", topArtistsService.getDashboardTopArtists(token));

        return "dashboard";
    }
}
