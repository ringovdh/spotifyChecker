package be.yorian.spotifyChecker_repository.repository;

import be.yorian.spotifyChecker_repository.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long>
{

    Track findBySpotifyId(String id);

    List<Track> findTop30ByOrderByTimesPlayedDesc();
}
