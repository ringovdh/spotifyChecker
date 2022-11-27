package be.yorian.spotifyChecker_repository.repository;

import be.yorian.spotifyChecker_repository.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>
{
    Optional<Artist> findBySpotifyId(String spotifyId);
}
