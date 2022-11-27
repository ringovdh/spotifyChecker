package be.yorian.spotifyChecker_model.dto;

import java.util.ArrayList;
import java.util.List;

public class TopArtistsDTO {

    private List<ArtistDTO> artists = new ArrayList<>();

    public TopArtistsDTO(){}

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }
}
