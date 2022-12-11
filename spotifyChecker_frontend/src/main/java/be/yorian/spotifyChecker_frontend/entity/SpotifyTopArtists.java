package be.yorian.spotifyChecker_frontend.entity;

import java.util.ArrayList;
import java.util.List;

public class SpotifyTopArtists {

    private List<SpotifyArtist> artists = new ArrayList<>();

    public SpotifyTopArtists(){}

    public List<SpotifyArtist> getArtists() {
        return artists;
    }

    public void setArtists(List<SpotifyArtist> artists) {
        this.artists = artists;
    }
}
