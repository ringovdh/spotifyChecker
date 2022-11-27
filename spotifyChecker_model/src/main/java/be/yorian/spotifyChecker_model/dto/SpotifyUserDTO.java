package be.yorian.spotifyChecker_model.dto;

public class SpotifyUserDTO {

    private String userName;

    public SpotifyUserDTO(String userName) {
        this.userName = userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
}
