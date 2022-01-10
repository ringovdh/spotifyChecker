package be.yorian.spotifychecker.service;

import be.yorian.spotifychecker.dto.CurrentTrackDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class CurrentTrackService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/me/player/";


    public CurrentTrackService(){
        this.restTemplate = new RestTemplate();
    }

    public CurrentTrackDTO getCurrentTrack(String token) {
        LinkedHashMap currentTrackDetails = null;
        CurrentTrackDTO currentTrack = null;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<Object> response = restTemplate.exchange(URL+"currently-playing", HttpMethod.GET, entity, Object.class);
        LinkedHashMap result = (LinkedHashMap) response.getBody();
        if (result != null) {
            currentTrackDetails = (LinkedHashMap) result.get("item");
            if (currentTrackDetails != null) {
                String currentTitle = (String) currentTrackDetails.get("name");
                ArrayList currentArtists = (ArrayList) currentTrackDetails.get("artists");
                LinkedHashMap currentArtist = (LinkedHashMap) currentArtists.get(0);
                LinkedHashMap album = (LinkedHashMap) currentTrackDetails.get("album");
                ArrayList images = (ArrayList) album.get("images");
                LinkedHashMap image = (LinkedHashMap) images.get(0);
                currentTrack = new CurrentTrackDTO(currentTitle);
                currentTrack.setArtist((String) currentArtist.get("name"));
                currentTrack.setImage((String) image.get("url"));
            }
        } else {
            currentTrack = new CurrentTrackDTO("Er wordt hier precies naar niets geluisterd");
            currentTrack.setArtist("");
            currentTrack.setImage("images/no-music.png");
        }
        return currentTrack;
    }
}
