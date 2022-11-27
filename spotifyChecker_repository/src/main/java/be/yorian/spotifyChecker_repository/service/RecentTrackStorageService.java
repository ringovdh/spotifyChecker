package be.yorian.spotifyChecker_repository.service;

import be.yorian.spotifyChecker_model.dto.TrackDTO;
import be.yorian.spotifyChecker_repository.model.Artist;
import be.yorian.spotifyChecker_repository.model.Track;
import be.yorian.spotifyChecker_repository.repository.ArtistRepository;
import be.yorian.spotifyChecker_repository.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecentTrackStorageService {

    private final TrackRepository trackRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public RecentTrackStorageService(
            TrackRepository trackRepository,
            ArtistRepository artistRepository)
    {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
    }


    public Track storeRecentPlayedTrack(TrackDTO trackDTO)
    {
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

        return track;
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
}
