package be.yorian.spotifyChecker_business.controller;

import be.yorian.spotifyChecker_connector.service.AccessTokenService;
import be.yorian.spotifyChecker_model.dto.SpotifyAccessTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class  AccessTokenController {

    private AccessTokenService accessTokenService;

    @Autowired
    public AccessTokenController(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @GetMapping("/spotifyChecker/login/url")
    public SpotifyAccessTokenDTO getAuthorizationURL() {
        return accessTokenService.getAuthorizationURL();
    }

    @PostMapping("/spotifyChecker/login/accessToken")
    public SpotifyAccessTokenDTO getAccessToken(@RequestHeader("code") String code) {
        return accessTokenService.getAccessToken(code);
    }


}
