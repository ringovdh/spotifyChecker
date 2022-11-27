package be.yorian.spotifyChecker_model.response;

public class SpotifyArtist {

    private String name;
    private String image;

    public SpotifyArtist(){}

    public SpotifyArtist(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getImage() { return image; }
}
