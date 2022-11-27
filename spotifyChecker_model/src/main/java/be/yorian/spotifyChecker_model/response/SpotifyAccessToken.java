package be.yorian.spotifyChecker_model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyAccessToken {

    private String token;
    private String authorizationURL;


    public SpotifyAccessToken() {
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getAuthorizationURL() {
        return authorizationURL;
    }

    public void setAuthorizationURL(String authorizationURL) {
        this.authorizationURL = authorizationURL;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", authorizationURL='" + authorizationURL +
                '}';
    }
}
