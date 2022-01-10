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
public class RecentPlayedService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/me/player/recently-played?limit=50";

    public RecentPlayedService() {
        this.restTemplate = new RestTemplate();
    }


    public TopTracksDTO getRecentPlayedTracks(String token) {

        TopTracksDTO recentPlayed = new TopTracksDTO();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<Object> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Object.class);
        LinkedHashMap result = (LinkedHashMap) response.getBody();
        if (result != null) {
            ArrayList items = (ArrayList) result.get("items");
            if (items.size() > 0) {
                for (Object item: items) {
                    LinkedHashMap details = (LinkedHashMap)item;
                    LinkedHashMap trackDetails = (LinkedHashMap) details.get("track");

                    String title = (String) trackDetails.get("name");
                    ArrayList currentArtists = (ArrayList) trackDetails.get("artists");
                    LinkedHashMap currentArtist = (LinkedHashMap) currentArtists.get(0);
                    LinkedHashMap album = (LinkedHashMap) trackDetails.get("album");
                    ArrayList images = (ArrayList) album.get("images");
                    LinkedHashMap image = (LinkedHashMap) images.get(0);
                    CurrentTrackDTO track = new CurrentTrackDTO(title);
                    track.setArtist((String) currentArtist.get("name"));
                    track.setImage((String) image.get("url"));
                    recentPlayed.getTracks().add(track);
                }
            }
        }

        return recentPlayed;
    }
}
