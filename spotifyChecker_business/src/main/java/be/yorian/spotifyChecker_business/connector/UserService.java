package be.yorian.spotifyChecker_business.connector;

import be.yorian.spotifyChecker_model.dto.SpotifyUserDTO;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class UserService extends CommonService {

    private static final String URL = "https://api.spotify.com/v1/me";


    public SpotifyUserDTO getUser(String accesToken)
    {
        SpotifyUserDTO user = null;
        LinkedHashMap userDetails = sendRequest(accesToken, URL);

        if (userDetails != null) {
            String userName = (String) userDetails.get("display_name");
            user = new SpotifyUserDTO(userName);
        }

        return user;
    }
}
