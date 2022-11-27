package be.yorian.spotifyChecker_frontend.controller;

import be.yorian.spotifyChecker_model.response.SpotifyUser;
import be.yorian.spotifyChecker_frontend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CallbackController {

    @Value( "${application.name}" )
    private String appName;
    private final LoginService loginService;
    private final UserService userService;
    private final CurrentTrackService currentTrackService;
    private final TopTracksService topTracksService;
    private final TopArtistsService topArtistsService;

    @Autowired
    public CallbackController(LoginService loginService,
                              UserService userService,
                              CurrentTrackService currentTrackService,
                              TopTracksService topTracksService,
                              TopArtistsService topArtistsService)
    {
        this.loginService = loginService;
        this.userService = userService;
        this.currentTrackService = currentTrackService;
        this.topTracksService = topTracksService;
        this.topArtistsService = topArtistsService;
    }

    @GetMapping("/callback")
    public String dashboard(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "error", required = false) String error,
            Model model,
            final HttpSession session)
    {
        String accessToken = loginService.getAccessToken(code);
        SpotifyUser user = userService.getSpotifyUser(accessToken);

        model.addAttribute("appName", appName);
        model.addAttribute("accessToken", accessToken);
        model.addAttribute("user", user);
        model.addAttribute("currentTrack", currentTrackService.getCurrentTrack(accessToken));
        model.addAttribute("topTracks", topTracksService.getSpotifyTopTracks(accessToken, "5"));
        model.addAttribute("topArtists", topArtistsService.getSpotifyTopArtists(accessToken, "5"));

        session.setAttribute("code", code);
        session.setAttribute("accessToken", accessToken);

        return "dashboard";
    }

}
