package be.yorian.spotifyChecker_repository.controller;

import be.yorian.spotifyChecker_model.dto.TopTracksDTO;
import be.yorian.spotifyChecker_repository.service.RecentTrackStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RecentTracksStorageController {

    public final RecentTrackStorageService recentTrackStorageService;

    @Autowired
    public RecentTracksStorageController(RecentTrackStorageService recentTrackStorageService)
    {
        this.recentTrackStorageService = recentTrackStorageService;
    }

    public void storeRecentPlayedTracks(TopTracksDTO topTracks)
    {
        topTracks.getTracks().forEach(recentTrackStorageService::storeRecentPlayedTrack);
    }
}
