package be.yorian.spotifychecker.service;

import be.yorian.spotifychecker.dto.CurrentTrackDTO;
import be.yorian.spotifychecker.dto.TopTracksDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class TopTracksService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/me/top/tracks?time_range=";

    public TopTracksService()
    {
        this.restTemplate = new RestTemplate();
    }


    public TopTracksDTO getDashboardTopTracks(String token)
    {
        TopTracksDTO topTracks = new TopTracksDTO();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<Object> response = restTemplate.exchange(URL+"short_term", HttpMethod.GET, entity, Object.class);
        LinkedHashMap result = (LinkedHashMap) response.getBody();
        if (result != null) {
            ArrayList items = (ArrayList) result.get("items");
            if (items.size() > 0) {
                for (int i = 0; i < 5; i++) {
                    LinkedHashMap item = (LinkedHashMap) items.get(i);

                    String currentTitle = (String) item.get("name");
                    ArrayList currentArtists = (ArrayList) item.get("artists");
                    LinkedHashMap currentArtist = (LinkedHashMap) currentArtists.get(0);
                    CurrentTrackDTO track = new CurrentTrackDTO(currentTitle);
                    track.setArtist((String) currentArtist.get("name"));
                    topTracks.getTracks().add(track);
                }
            }
        }

        return topTracks;
    }
}
