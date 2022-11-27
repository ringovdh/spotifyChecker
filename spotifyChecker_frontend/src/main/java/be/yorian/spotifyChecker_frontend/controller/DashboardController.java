package be.yorian.spotifyChecker_frontend.controller;

import be.yorian.spotifyChecker_frontend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @Value( "${application.name}" )
    private String appName;
    private final UserService  userService;
    private final CurrentTrackService currentTrackService;
    private final TopTracksService topTracksService;
    private final TopArtistsService topArtistsService;

    @Autowired
    public DashboardController(UserService  userService,
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

        model.addAttribute("appName", appName);
        model.addAttribute("accessToken", token);
        model.addAttribute("user", userService.getSpotifyUser(token));
        model.addAttribute("currentTrack", currentTrackService.getCurrentTrack(token));
        model.addAttribute("topTracks", topTracksService.getSpotifyTopTracks(token, "5"));
        model.addAttribute("topArtists", topArtistsService.getSpotifyTopArtists(token, "5"));

        return "dashboard";
    }
}
