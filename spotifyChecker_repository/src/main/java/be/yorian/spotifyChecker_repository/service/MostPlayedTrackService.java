package be.yorian.spotifyChecker_repository.service;

import be.yorian.spotifyChecker_model.dto.ArtistDTO;
import be.yorian.spotifyChecker_model.dto.TopTracksDTO;
import be.yorian.spotifyChecker_model.dto.TrackDTO;
import be.yorian.spotifyChecker_repository.model.Track;
import be.yorian.spotifyChecker_repository.repository.ArtistRepository;
import be.yorian.spotifyChecker_repository.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MostPlayedTrackService {

    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public MostPlayedTrackService(
            TrackRepository trackRepository,
            ArtistRepository artistRepository)
    {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
    }


    public TopTracksDTO getMostPlayedTracks()
    {
        TopTracksDTO mostPlayed = new TopTracksDTO();
        List<Track> tracks = trackRepository.findTop30ByOrderByTimesPlayedDesc();
        List<TrackDTO> trackDTOList = new ArrayList<>();

        for (Track track : tracks) {
            TrackDTO trackDTO = mapTrackToDTO(track);
            trackDTOList.add(trackDTO);
        }
        mostPlayed.setTracks(trackDTOList);

        return mostPlayed;
    }

    private TrackDTO mapTrackToDTO(Track track)
    {
        ArtistDTO artistDTO = new ArtistDTO(track.getArtist().getName());
        TrackDTO trackDTO = new TrackDTO(track.getTitle());
        trackDTO.setSpotifyId(track.getSpotifyId());
        trackDTO.setTimesPlayed(track.getTimesPlayed());
        trackDTO.setArtist(artistDTO);
        trackDTO.setPlayedOn(track.getPlayedOn());

        return trackDTO;
    }

}
