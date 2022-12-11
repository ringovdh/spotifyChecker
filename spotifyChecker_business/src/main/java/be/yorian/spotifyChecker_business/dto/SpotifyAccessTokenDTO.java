package be.yorian.spotifyChecker_business.dto;

public class SpotifyAccessTokenDTO {

    private String token;
    private final String authorizationURL;

    public SpotifyAccessTokenDTO(String url, String token)
    {
        this.authorizationURL = url;
        this.token = token;
    }


    public String getToken()
    {
        return this.token;
    }
    public String getAuthorizationURL()
    {
        return this.authorizationURL;
    }

}
