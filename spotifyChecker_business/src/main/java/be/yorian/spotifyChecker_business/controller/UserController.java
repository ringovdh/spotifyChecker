package be.yorian.spotifyChecker_business.controller;

import be.yorian.spotifyChecker_business.connector.UserService;
import be.yorian.spotifyChecker_model.dto.SpotifyUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }


    @PostMapping("/spotifyChecker/user")
    public SpotifyUserDTO getUser(@RequestHeader("accessToken") String accessToken)
    {
        SpotifyUserDTO user = userService.getUser(accessToken);
        return user;
    }
}
