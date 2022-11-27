package be.yorian.spotifyChecker_frontend.controller;

import be.yorian.spotifyChecker_frontend.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Value( "${application.name}" )
    private String appName;

    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("login", service.getAuthorizationURL());

        return "home";
    }
}
