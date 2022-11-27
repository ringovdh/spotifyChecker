package be.yorian.spotifyChecker_model.dto;

import java.time.LocalDateTime;

public class TrackDTO {

    private String id;
    private final String title;
    private ArtistDTO artist;
    private String image;
    private LocalDateTime playedOn;
    private int timesPlayed;
    private String spotifyId;

    public TrackDTO(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getPlayedOn() {
        return this.playedOn;
    }

    public void setPlayedOn(LocalDateTime playedOn) {
        this.playedOn = playedOn;
    }

    public String getSpotifyId() {
        return this.spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public int getTimesPlayed() {
        return this.timesPlayed;
    }
    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }
}
