package be.yorian.spotifyChecker_business.connector;

import be.yorian.spotifyChecker_model.dto.SpotifyAccessTokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.LinkedHashMap;

@Service
public class AccessTokenService {

    @Value( "${spotify.app.client-id}" )
    private String clientId;
    @Value( "${spotify.app.redirect-url}" )
    private String redirectUrl;
    private final RestTemplate restTemplate;
    private final String secretCode;
    private static final String URL = "https://accounts.spotify.com/api/token";


    public AccessTokenService(){
        this.restTemplate = new RestTemplate();
        this.secretCode = generateSecretCode();
    }


    public SpotifyAccessTokenDTO getAuthorizationURL() {

        String url = "https://accounts.spotify.com:443/authorize?" +
                "client_id=" + clientId +
                "&response_type=" + "code" +
                "&redirect_uri=" + redirectUrl +
                "&code_challenge_method=S256&code_challenge=" + generateCodeChallenge()+
                "&scope="   + "user-read-email,"
                            + "user-read-private,"
                            + "user-read-currently-playing,"
                            + "user-read-recently-played,"
                            + "user-top-read" +
                "&show_dialog=" + true;

        return new SpotifyAccessTokenDTO(url, null);
    }

    public SpotifyAccessTokenDTO getAccessToken(String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", redirectUrl);
        map.add("code_verifier", this.secretCode);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<Object> response = restTemplate.postForEntity(URL, request, Object.class);
        LinkedHashMap result = (LinkedHashMap) response.getBody();
        String token = (String) result.get("access_token");
        SpotifyAccessTokenDTO tokenDTO = new SpotifyAccessTokenDTO(null, token);

        return tokenDTO;
    }

    private String generateCodeChallenge() {

        byte[] digest = null;

        try {
            byte[] bytes = secretCode.getBytes("US-ASCII");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bytes, 0, bytes.length);
            digest = messageDigest.digest();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException exception) {
            System.out.println(exception.getLocalizedMessage());
        }

        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }

    private String generateSecretCode() {

        SecureRandom secureRandom = new SecureRandom();
        byte[] codeVerifier = new byte[32];
        secureRandom.nextBytes(codeVerifier);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);

    }

}
