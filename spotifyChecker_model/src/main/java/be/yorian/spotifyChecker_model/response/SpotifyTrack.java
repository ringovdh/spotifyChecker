package be.yorian.spotifyChecker_model.response;

public class SpotifyTrack {

    private String title;
    private SpotifyArtist artist;
    private String image;
    private int timesPlayed;

    public SpotifyTrack(){}

    public SpotifyTrack(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public SpotifyArtist getArtist() {
        return artist;
    }

    public void setArtist(SpotifyArtist artist) {
        this.artist = artist;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArtistName() {
        return artist != null ? artist.getName() : "";
    }

    public String getTimesPlayed() {
        return ""+this.timesPlayed;
    }
    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

}
