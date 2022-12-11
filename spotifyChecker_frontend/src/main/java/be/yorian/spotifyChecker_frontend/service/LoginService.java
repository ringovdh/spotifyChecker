package be.yorian.spotifyChecker_frontend.service;

import be.yorian.spotifyChecker_frontend.entity.SpotifyAccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService
{
    private final RestTemplate restTemplate;
    @Value( "${backend.url.login}" )
    private String URL;


    public LoginService()
    {
        this.restTemplate = new RestTemplate();
    }

    public String getAuthorizationURL()
    {
        SpotifyAccessToken token = restTemplate.getForObject(URL + "/url", SpotifyAccessToken.class);

        return token.getAuthorizationURL();
    }

    public String getAccessToken(String code)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("code", code);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        SpotifyAccessToken token = restTemplate.postForObject(URL + "/accessToken", entity, SpotifyAccessToken.class);

        return token.getToken();
    }


}
