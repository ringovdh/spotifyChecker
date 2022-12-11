package be.yorian.spotifyChecker_business.dto;

import java.util.ArrayList;
import java.util.List;

public class TopTracksDTO {

    private List<TrackDTO> tracks = new ArrayList<>();

    public TopTracksDTO() {

    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }


}
