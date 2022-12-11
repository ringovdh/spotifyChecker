package be.yorian.spotifyChecker_frontend.entity;

import java.util.ArrayList;
import java.util.List;

public class SpotifyTopTracks {

    private List<SpotifyTrack> tracks = new ArrayList<>();

    public SpotifyTopTracks() {

    }

    public List<SpotifyTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<SpotifyTrack> tracks) {
        this.tracks = tracks;
    }


}
