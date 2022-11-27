package be.yorian.spotifyChecker_repository.controller;

import be.yorian.spotifyChecker_model.dto.TopTracksDTO;
import be.yorian.spotifyChecker_repository.service.MostPlayedTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MostPlayedTracksController {

    private final MostPlayedTrackService mostPlayedTrackService;

    @Autowired
    public MostPlayedTracksController(MostPlayedTrackService mostPlayedTrackService)
    {
        this.mostPlayedTrackService = mostPlayedTrackService;
    }

    public TopTracksDTO mostPlayedPlayedTracks()
    {
        return mostPlayedTrackService.getMostPlayedTracks();
    }
}
