package be.yorian.spotifyChecker_frontend.service;

import be.yorian.spotifyChecker_model.response.SpotifyUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService
{
    private final RestTemplate restTemplate;

    @Value( "${backend.url.user}" )
    private String USER_URL;

    public UserService() {
        this.restTemplate = new RestTemplate();
    }

    public SpotifyUser getSpotifyUser(String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("accessToken", accessToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        SpotifyUser user = restTemplate.postForObject(USER_URL, entity, SpotifyUser.class);

        return user;
    }
}
