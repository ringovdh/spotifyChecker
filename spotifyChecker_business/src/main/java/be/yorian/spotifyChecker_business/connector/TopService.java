package be.yorian.spotifyChecker_business.connector;

import be.yorian.spotifyChecker_business.dto.ArtistDTO;
import be.yorian.spotifyChecker_business.dto.TopArtistsDTO;
import be.yorian.spotifyChecker_business.dto.TopTracksDTO;
import be.yorian.spotifyChecker_business.dto.TrackDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class TopService extends CommonService {

    private static final String TRACK_URL = "https://api.spotify.com/v1/me/top/tracks?time_range=";
    private static final String ARTIST_URL = "https://api.spotify.com/v1/me/top/artists?time_range=";

    public TopService()
    {
    }


    public TopTracksDTO getTopTrack(String accessToken, String term, int limit)
    {
        TopTracksDTO topTracks = new TopTracksDTO();
        LinkedHashMap result = sendRequest(accessToken,TRACK_URL + term);

        if (result != null) {
            ArrayList items = (ArrayList) result.get("items");
            if (limit == 0) {
                limit = items.size();
            }
            if (items.size() > 0) {
                for (int i = 0; i < limit; i++) {
                    LinkedHashMap item = (LinkedHashMap) items.get(i);
                    ArrayList currentArtists = (ArrayList) item.get("artists");
                    LinkedHashMap currentArtist = (LinkedHashMap) currentArtists.get(0);

                    String artistName = (String) currentArtist.get("name");
                    ArtistDTO artist = new ArtistDTO(artistName);
                    LinkedHashMap album = (LinkedHashMap) item.get("album");
                    ArrayList images = (ArrayList) album.get("images");
                    LinkedHashMap image = (LinkedHashMap) images.get(0);

                    String currentTitle = (String) item.get("name");
                    TrackDTO track = new TrackDTO(currentTitle);
                    track.setArtist(artist);
                    track.setImage((String) image.get("url"));
                    topTracks.getTracks().add(track);
                }
            }
        }

        return topTracks;
    }


    public TopArtistsDTO getTopArtists(String accessToken, String term, int limit)
    {
        TopArtistsDTO topArtists = new TopArtistsDTO();
        LinkedHashMap result = sendRequest(accessToken,ARTIST_URL + term);

        if (result != null) {
            ArrayList items = (ArrayList) result.get("items");
            if (limit == 0) {
                limit = items.size();
            }
            if (items.size() > 0) {
                for (int i = 0; i < limit; i++) {
                    LinkedHashMap item = (LinkedHashMap) items.get(i);
                    String artistName = (String) item.get("name");
                    ArrayList images = (ArrayList) item.get("images");
                    LinkedHashMap image = (LinkedHashMap) images.get(0);

                    ArtistDTO artist = new ArtistDTO(artistName);
                    artist.setImage((String) image.get("url"));

                    topArtists.getArtists().add(artist);
                }
            }
        }
        return topArtists;
    }
}
