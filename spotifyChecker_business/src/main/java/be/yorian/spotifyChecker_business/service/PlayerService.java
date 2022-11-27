package be.yorian.spotifyChecker_business.service;

import be.yorian.spotifyChecker_connector.service.ConnectorPlayerService;
import be.yorian.spotifyChecker_model.dto.TopTracksDTO;
import be.yorian.spotifyChecker_model.dto.TrackDTO;
import be.yorian.spotifyChecker_repository.controller.MostPlayedTracksController;
import be.yorian.spotifyChecker_repository.controller.RecentTracksStorageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final ConnectorPlayerService connectorPlayerService;
    private final MostPlayedTracksController mostPlayedTracksController;
    private final RecentTracksStorageController recentTracksStorageController;

    @Autowired
    public PlayerService(ConnectorPlayerService connectorPlayerService,
                         RecentTracksStorageController recentTracksStorageController,
                         MostPlayedTracksController mostPlayedTracksController)
    {
        this.connectorPlayerService = connectorPlayerService;
        this.recentTracksStorageController = recentTracksStorageController;
        this.mostPlayedTracksController = mostPlayedTracksController;
    }

    public TrackDTO getCurrentPlayingTrack(String accessToken)
    {
        return connectorPlayerService.getCurrentPlayingTrack(accessToken);
    }

    public TopTracksDTO getRecentPlayedTracks(String accessToken)
    {
        TopTracksDTO topTracks = connectorPlayerService.getRecentPlayedTracks(accessToken);
        recentTracksStorageController.storeRecentPlayedTracks(topTracks);

        return topTracks;
    }

    public TopTracksDTO getMostPlayedTracks() {
        return mostPlayedTracksController.mostPlayedPlayedTracks();
    }
}
