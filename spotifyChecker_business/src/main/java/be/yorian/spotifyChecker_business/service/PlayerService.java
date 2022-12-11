package be.yorian.spotifyChecker_business.service;

import be.yorian.spotifyChecker_business.connector.ConnectorPlayerService;
import be.yorian.spotifyChecker_business.dto.ArtistDTO;
import be.yorian.spotifyChecker_business.dto.TopTracksDTO;
import be.yorian.spotifyChecker_business.dto.TrackDTO;
import be.yorian.spotifyChecker_business.entity.Artist;
import be.yorian.spotifyChecker_business.entity.Track;
import be.yorian.spotifyChecker_business.repository.ArtistRepository;
import be.yorian.spotifyChecker_business.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final ConnectorPlayerService connectorPlayerService;
    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public PlayerService(ConnectorPlayerService connectorPlayerService,
                         TrackRepository trackRepository,
                         ArtistRepository artistRepository)
    {
        this.connectorPlayerService = connectorPlayerService;
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
    }

    public TrackDTO getCurrentPlayingTrack(String accessToken)
    {
        return connectorPlayerService.getCurrentPlayingTrack(accessToken);
    }

    public TopTracksDTO getRecentPlayedTracks(String accessToken)
    {
        TopTracksDTO topTracks = connectorPlayerService.getRecentPlayedTracks(accessToken);

        topTracks.getTracks()
                .forEach(trackDTO -> {
                    Track track = trackRepository.findBySpotifyId(trackDTO.getSpotifyId());

                    if (null == track) {
                        track = mapTrack(trackDTO);
                    } else if (trackDTO.getPlayedOn().isAfter(track.getPlayedOn())) {
                        track.setPlayedOn(trackDTO.getPlayedOn());
                        track.setTimesPlayed(track.getTimesPlayed() + 1);
                        Artist artist = track.getArtist();
                        if (null != artist) {
                            artist.setTimesPlayed(artist.getTimesPlayed() + 1);
                        }
                    }
                    trackRepository.save(track);
                });

        return topTracks;
    }

    private Track mapTrack(TrackDTO trackDTO)
    {
        Optional<Artist> artistOptional = artistRepository.findBySpotifyId(trackDTO.getArtist().getSpotifyId());
        Artist artist = artistOptional.orElseGet(Artist::new);

        artist.setName(trackDTO.getArtist().getName());
        artist.setSpotifyId(trackDTO.getArtist().getSpotifyId());
        artist.setTimesPlayed(artist.getTimesPlayed() + 1);

        Track track = new Track();
        track.setTitle(trackDTO.getTitle());
        track.setSpotifyId(trackDTO.getSpotifyId());
        track.setTimesPlayed(1);
        track.setArtist(artist);
        track.setPlayedOn(trackDTO.getPlayedOn());

        return track;
    }

    public TopTracksDTO getMostPlayedTracks() {
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
