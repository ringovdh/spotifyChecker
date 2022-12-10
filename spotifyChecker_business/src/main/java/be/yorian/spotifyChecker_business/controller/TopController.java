package be.yorian.spotifyChecker_business.controller;

import be.yorian.spotifyChecker_business.connector.TopService;
import be.yorian.spotifyChecker_model.dto.TopArtistsDTO;
import be.yorian.spotifyChecker_model.dto.TopTracksDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopController {

    private TopService topService;

    @Autowired
    public TopController(TopService topService) {
        this.topService = topService;
    }

    @PostMapping("/spotifyChecker/top/tracks")
    public TopTracksDTO getTopTracks(
            @RequestHeader("accessToken") String accessToken,
            @RequestHeader("term") String term,
            @RequestHeader(value="limit", defaultValue="0") String limit) {

        return topService.getTopTrack(accessToken, term, Integer.parseInt(limit));
    }

    @PostMapping("/spotifyChecker/top/artists")
    public TopArtistsDTO getTopArtists(
            @RequestHeader("accessToken") String accessToken,
            @RequestHeader("term") String term,
            @RequestHeader(value="limit", defaultValue="0") String limit) {

        return topService.getTopArtists(accessToken, term, Integer.parseInt(limit));
    }
}
