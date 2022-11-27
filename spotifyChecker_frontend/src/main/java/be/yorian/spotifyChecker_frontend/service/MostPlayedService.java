package be.yorian.spotifyChecker_frontend.service;

import be.yorian.spotifyChecker_model.response.SpotifyTopTracks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MostPlayedService {

    private final RestTemplate restTemplate;

    @Value("${backend.url.player}")
    private String PLAYER_URL;


    public MostPlayedService()
    {
        this.restTemplate = new RestTemplate();
    }


    public SpotifyTopTracks getMostPlayedTracks()
    {
        SpotifyTopTracks mostPlayedTracks;

        HttpEntity<String> entity = new HttpEntity<>("parameters");
        mostPlayedTracks = restTemplate.postForObject(PLAYER_URL + "/mostPlaying", entity, SpotifyTopTracks.class);

        return mostPlayedTracks;
    }

}
