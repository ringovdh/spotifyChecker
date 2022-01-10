package be.yorian.spotifychecker.service;

import be.yorian.spotifychecker.dto.UserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    private static final String URL = "https://api.spotify.com/v1/me";


    public UserService(){
        this.restTemplate = new RestTemplate();
    }


    public UserDTO getUser(String token) {

        UserDTO user = null;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<Object> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Object.class);
        LinkedHashMap userDetails = (LinkedHashMap) response.getBody();
        if (userDetails != null) {
            String userName = (String) userDetails.get("display_name");
            user = new UserDTO(userName);
        }

        return user;
    }
}
