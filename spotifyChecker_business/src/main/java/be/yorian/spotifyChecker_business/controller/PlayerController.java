package be.yorian.spotifyChecker_business.controller;

import be.yorian.spotifyChecker_business.service.PlayerService;
import be.yorian.spotifyChecker_model.dto.TopTracksDTO;
import be.yorian.spotifyChecker_model.dto.TrackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService)
    {
        this.playerService = playerService;
    }

    @PostMapping("/spotifyChecker/player/currentPlaying")
    public TrackDTO getCurrentTrack(@RequestHeader("accessToken") String accessToken)
    {
        return playerService.getCurrentPlayingTrack(accessToken);
    }

    @PostMapping("/spotifyChecker/player/recentPlaying")
    public TopTracksDTO getRecentPlayedTracks(@RequestHeader("accessToken") String accessToken)
    {
        return playerService.getRecentPlayedTracks(accessToken);
    }

    @PostMapping("/spotifyChecker/player/mostPlaying")
    public TopTracksDTO getMostPlayedTracks()
    {
        return playerService.getMostPlayedTracks();
    }
}
