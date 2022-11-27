package be.yorian.spotifyChecker_repository;

import be.yorian.spotifyChecker_model.dto.ArtistDTO;
import be.yorian.spotifyChecker_model.dto.TrackDTO;
import be.yorian.spotifyChecker_repository.model.Artist;
import be.yorian.spotifyChecker_repository.model.Track;
import be.yorian.spotifyChecker_repository.repository.ArtistRepository;
import be.yorian.spotifyChecker_repository.repository.TrackRepository;
import be.yorian.spotifyChecker_repository.service.RecentTrackStorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecentTrackStorageServiceTest
{

    @Mock
    private TrackRepository trackRepository;
    @Mock
    private ArtistRepository artistRepository;
    @InjectMocks
    private RecentTrackStorageService recentTrackStorageService;
    private Track track;

    @Before
    public void setUp()
    {
        Artist artist = new Artist();
        artist.setTimesPlayed(4);
        artist.setName("TC Matic");
        track = new Track();
        track.setTitle("Oh la la la");
        track.setTimesPlayed(2);
        LocalDateTime today = LocalDateTime. now();
        LocalDateTime yesterday = today. minusDays(1);
        track.setPlayedOn(yesterday);
        track.setArtist(artist);
        track.setSpotifyId("6SXy02aTZU3ysoGUixYCz0");
    }

    @Test
    public void whenSpotifyIdExists_trackTimesPlayedIsEncreased()
    {
        TrackDTO trackDTO = new TrackDTO("Oh la la la");
        trackDTO.setSpotifyId("6SXy02aTZU3ysoGUixYCz0");
        trackDTO.setPlayedOn(LocalDateTime. now());

        when(trackRepository.findBySpotifyId("6SXy02aTZU3ysoGUixYCz0")).thenReturn(track);

        Track foundTrack = recentTrackStorageService.storeRecentPlayedTrack(trackDTO);

        assertThat(foundTrack.getTimesPlayed()).isEqualTo(3);
    }

    @Test
    public void whenSpotifyIdNotExists_newTrackIsCreated()
    {
        TrackDTO trackDTO = new TrackDTO("Putain putain");
        trackDTO.setSpotifyId("1iFIZUVDBCCkWe705FLXto");
        when(trackRepository.findBySpotifyId("1iFIZUVDBCCkWe705FLXto")).thenReturn(null);
        Track foundTrack = recentTrackStorageService.storeRecentPlayedTrack(trackDTO);
        assertThat(foundTrack.getTitle()).isEqualTo("Putain putain");
        assertThat(foundTrack.getTimesPlayed()).isEqualTo(1);
    }

    @Test
    public void whenSpotifyIdExists_artistTimesPlayedIsEncreased()
    {
        TrackDTO trackDTO = new TrackDTO("Oh la la la");
        trackDTO.setSpotifyId("6SXy02aTZU3ysoGUixYCz0");
        ArtistDTO artistDTO = new ArtistDTO("TC Matic");
        trackDTO.setArtist(artistDTO);
        when(trackRepository.findBySpotifyId("6SXy02aTZU3ysoGUixYCz0")).thenReturn(track);
        Track foundTrack = recentTrackStorageService.storeRecentPlayedTrack(trackDTO);
        assertThat(foundTrack.getArtist().getName()).isEqualTo("TC Matic");
        assertThat(foundTrack.getArtist().getTimesPlayed()).isEqualTo(5);
    }
}
