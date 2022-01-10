package be.yorian.spotifychecker.service;

import be.yorian.spotifychecker.dto.ArtistDTO;
import be.yorian.spotifychecker.dto.TopArtistsDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class TopArtistsService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/me/top/artists?time_range=";

    public TopArtistsService()
    {
        this.restTemplate = new RestTemplate();
    }

    public TopArtistsDTO getDashboardTopArtists(String token)
    {
        TopArtistsDTO topArtists = new TopArtistsDTO();
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
                    String artistName = (String) item.get("name");
                    ArtistDTO artist = new ArtistDTO(artistName);
                    topArtists.getArtists().add(artist);
                }
            }
        }
        return topArtists;
    }
}
