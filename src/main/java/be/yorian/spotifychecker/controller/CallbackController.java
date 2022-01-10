package be.yorian.spotifychecker.controller;

import be.yorian.spotifychecker.dto.UserDTO;
import be.yorian.spotifychecker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CallbackController {

    private final AccessTokenService accessTokenService;
    private final UserService userService;
    private final CurrentTrackService currentTrackService;
    private final TopTracksService topTracksService;
    private final TopArtistsService topArtistsService;

    @Autowired
    public CallbackController(AccessTokenService accessTokenService,
                              UserService userService,
                              CurrentTrackService currentTrackService,
                              TopTracksService topTracksService,
                              TopArtistsService topArtistsService)
    {
        this.accessTokenService = accessTokenService;
        this.userService = userService;
        this.currentTrackService = currentTrackService;
        this.topTracksService = topTracksService;
        this.topArtistsService = topArtistsService;
    }

    @GetMapping("/callback")
    public String callback(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "error", required = false) String error,
            Model model,
            final HttpSession session) {

        session.setAttribute("code", code);

        String token = accessTokenService.getAccessToken(code);
        session.setAttribute("accessToken", token);
        model.addAttribute("accessToken", token);
        UserDTO user = userService.getUser(token);
        session.setAttribute("userName", user.getUserName());
        model.addAttribute("user", user);
        model.addAttribute("currentTrack", currentTrackService.getCurrentTrack(token));
        model.addAttribute("topTracks", topTracksService.getDashboardTopTracks(token));
        model.addAttribute("topArtists", topArtistsService.getDashboardTopArtists(token));

        return "dashboard";
    }

}
