package be.yorian.spotifyChecker_business.connector;

import be.yorian.spotifyChecker_business.dto.ArtistDTO;
import be.yorian.spotifyChecker_business.dto.TopTracksDTO;
import be.yorian.spotifyChecker_business.dto.TrackDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
public class ConnectorPlayerService extends CommonService {

    private static final String URL = "https://api.spotify.com/v1/me/player/";


    public ConnectorPlayerService()
    {

    }

    public TrackDTO getCurrentPlayingTrack(String accessToken)
    {
        LinkedHashMap currentTrackDetails;
        TrackDTO currentTrack = null;
        LinkedHashMap result = sendRequest(accessToken,URL+"currently-playing");

        if (result != null) {
            currentTrackDetails = (LinkedHashMap) result.get("item");
            if (currentTrackDetails != null) {
                currentTrack = convertResponseToTrackDTO(currentTrackDetails);
            }
        }

        return currentTrack;
    }

    public TopTracksDTO getRecentPlayedTracks(String accessToken)
    {
        TopTracksDTO recentPlayed = new TopTracksDTO();
        LinkedHashMap result = sendRequest(accessToken,URL + "recently-played?limit=50");

        if (result != null) {
            ArrayList items = (ArrayList) result.get("items");
            if (items.size() > 0) {
                for (Object item: items) {
                    LinkedHashMap trackDetails = (LinkedHashMap)item;
                    TrackDTO track = convertResponseToTrackDTO(trackDetails.get("track"));
                    String dateAsString = (String)trackDetails.get("played_at");
                    track.setPlayedOn(LocalDateTime.parse(dateAsString.substring(0,19)));
                    recentPlayed.getTracks().add(track);
                }
            }
        }

        return recentPlayed;
    }

    private TrackDTO convertResponseToTrackDTO(Object item) {

        LinkedHashMap trackDetails = (LinkedHashMap)item;

        ArrayList currentArtists = (ArrayList) trackDetails.get("artists");
        LinkedHashMap currentArtist = (LinkedHashMap) currentArtists.get(0);
        LinkedHashMap album = (LinkedHashMap) trackDetails.get("album");
        ArrayList images = (ArrayList) album.get("images");
        LinkedHashMap image = (LinkedHashMap) images.get(0);

        String artistName = (String) currentArtist.get("name");
        String artistSpotifyId = (String) currentArtist.get("id");
        ArtistDTO artist = new ArtistDTO(artistName);
        artist.setSpotifyId(artistSpotifyId);

        String title = (String) trackDetails.get("name");
        String spotifyId = (String) trackDetails.get("id");
        TrackDTO trackDTO = new TrackDTO(title);
        trackDTO.setArtist(artist);
        trackDTO.setImage((String) image.get("url"));
        trackDTO.setSpotifyId(spotifyId);

        return  trackDTO;
    }


}
