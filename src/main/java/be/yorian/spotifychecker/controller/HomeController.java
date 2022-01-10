package be.yorian.spotifychecker.controller;

import be.yorian.spotifychecker.service.AccessTokenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AccessTokenService service;

    public HomeController(AccessTokenService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", "Joe Box");
        model.addAttribute("login", service.getAuthorizationURL());

        return "home";
    }
}
