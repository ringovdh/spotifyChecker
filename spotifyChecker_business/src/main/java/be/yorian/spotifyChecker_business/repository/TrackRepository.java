package be.yorian.spotifyChecker_business.repository;

import be.yorian.spotifyChecker_business.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long>
{

    Track findBySpotifyId(String id);

    List<Track> findTop30ByOrderByTimesPlayedDesc();
}
