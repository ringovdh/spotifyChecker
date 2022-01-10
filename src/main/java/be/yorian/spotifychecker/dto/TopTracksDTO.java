package be.yorian.spotifychecker.dto;

import java.util.ArrayList;
import java.util.List;

public class TopTracksDTO {

    private List<CurrentTrackDTO> tracks = new ArrayList<>();

    public TopTracksDTO() {

    }

    public List<CurrentTrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<CurrentTrackDTO> tracks) {
        this.tracks = tracks;
    }


}
