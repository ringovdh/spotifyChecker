package be.yorian.spotifyChecker_frontend.service;

import be.yorian.spotifyChecker_model.response.SpotifyTopArtists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TopArtistsService {

    private final RestTemplate restTemplate;

    @Value("${backend.url.top}")
    private String TOP_URL;


    public TopArtistsService()
    {
        this.restTemplate = new RestTemplate();
    }


    public SpotifyTopArtists getSpotifyTopArtists(String accessToken, String limit)
    {
        SpotifyTopArtists topArtists = new SpotifyTopArtists();
        HttpHeaders headers = new HttpHeaders();
        headers.set("accessToken", accessToken);
        headers.set("term", "short_term");
        headers.set("limit", limit);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        topArtists = restTemplate.postForObject(TOP_URL + "/artists", entity, SpotifyTopArtists.class);

        return topArtists;
    }
}
