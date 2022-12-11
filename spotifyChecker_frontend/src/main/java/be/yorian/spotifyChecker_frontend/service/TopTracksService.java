package be.yorian.spotifyChecker_frontend.service;

import be.yorian.spotifyChecker_frontend.entity.SpotifyTopTracks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TopTracksService {

    private final RestTemplate restTemplate;

    @Value("${backend.url.top}")
    private  String TOP_URL;


    public TopTracksService()
    {
        this.restTemplate = new RestTemplate();
    }


    public SpotifyTopTracks getSpotifyTopTracks(String accessToken, String limit)
    {
        SpotifyTopTracks topTracks;
        HttpHeaders headers = new HttpHeaders();
        headers.set("accessToken", accessToken);
        headers.set("term", "short_term");
        headers.set("limit", limit);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        topTracks = restTemplate.postForObject(TOP_URL + "/tracks", entity, SpotifyTopTracks.class);

        return topTracks;
    }
}
