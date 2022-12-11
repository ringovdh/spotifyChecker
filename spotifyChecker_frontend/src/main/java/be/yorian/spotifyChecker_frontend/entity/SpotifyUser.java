package be.yorian.spotifyChecker_frontend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyUser {

    private String username;


    public SpotifyUser() {}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
