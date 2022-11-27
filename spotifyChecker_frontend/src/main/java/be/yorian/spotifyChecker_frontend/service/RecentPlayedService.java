package be.yorian.spotifyChecker_frontend.service;

import be.yorian.spotifyChecker_model.response.SpotifyTopTracks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecentPlayedService {

    private final RestTemplate restTemplate;

    @Value("${backend.url.player}")
    private String PLAYER_URL;


    public RecentPlayedService()
    {
        this.restTemplate = new RestTemplate();
    }


    public SpotifyTopTracks getRecentPlayedTracks(String accessToken)
    {
        SpotifyTopTracks recentTracks;
        HttpHeaders headers = new HttpHeaders();
        headers.set("accessToken", accessToken);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        recentTracks = restTemplate.postForObject(PLAYER_URL + "/recentPlaying", entity, SpotifyTopTracks.class);

        return recentTracks;
    }

}
