package be.yorian.spotifyChecker_frontend.service;


import be.yorian.spotifyChecker_model.response.SpotifyTrack;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrentTrackService {

    private final RestTemplate restTemplate;

    @Value("${backend.url.player}")
    private String PLAYER_URL;


    public CurrentTrackService(){
        this.restTemplate = new RestTemplate();
    }

    public SpotifyTrack getCurrentTrack(String accessToken)
    {
        SpotifyTrack track;
        HttpHeaders headers = new HttpHeaders();
        headers.set("accessToken", accessToken);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        track = restTemplate.postForObject(PLAYER_URL + "/currentPlaying", entity, SpotifyTrack.class);

        if (track == null) {
            track = new SpotifyTrack("Er wordt hier precies naar niets geluisterd");
            //track.setArtist(null);
            track.setImage("images/no-music.png");
        }
        return track;
    }
}
