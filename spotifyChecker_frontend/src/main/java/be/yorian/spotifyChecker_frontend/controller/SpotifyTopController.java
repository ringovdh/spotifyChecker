package be.yorian.spotifyChecker_frontend.controller;

import be.yorian.spotifyChecker_frontend.service.TopArtistsService;
import be.yorian.spotifyChecker_frontend.service.TopTracksService;
import be.yorian.spotifyChecker_frontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SpotifyTopController {

    @Value( "${application.name}" )
    private String appName;
    private final UserService  userService;
    private final TopTracksService topTracksService;
    private final TopArtistsService topArtistsService;

    @Autowired
    public SpotifyTopController(UserService userService,
                               TopTracksService topTracksService,
                               TopArtistsService topArtistsService)
    {
        this.userService = userService;
        this.topTracksService = topTracksService;
        this.topArtistsService = topArtistsService;
    }

    @GetMapping("/spotifyTopArtist")
    public String spotifyTopArtist(Model model, final HttpSession session)
    {
        String token = (String) session.getAttribute("accessToken");
        model.addAttribute("appName", appName);
        model.addAttribute("accessToken", token);
        model.addAttribute("user", userService.getSpotifyUser(token));
        model.addAttribute("topArtists", topArtistsService.getSpotifyTopArtists(token, "0"));

        return "spotifyTopArtist";
    }

    @GetMapping("/spotifyTopTrack")
    public String spotifyTopTrack(Model model, final HttpSession session)
    {
        String token = (String) session.getAttribute("accessToken");
        model.addAttribute("appName", appName);
        model.addAttribute("accessToken", token);
        model.addAttribute("user", userService.getSpotifyUser(token));
        model.addAttribute("topTracks", topTracksService.getSpotifyTopTracks(token, "0"));

        return "spotifyTopTrack";
    }
}
