package be.yorian.spotifyChecker_business.dto;

public class ArtistDTO {

    private String name;
    private String spotifyId;
    private String image;

    public ArtistDTO(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
