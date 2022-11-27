package be.yorian.spotifyChecker_frontend.controller;

import be.yorian.spotifyChecker_frontend.service.MostPlayedService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MostPlayedController {

    @Value( "${application.name}" )
    private String appName;
    private final MostPlayedService mostPlayedService;

    public MostPlayedController(MostPlayedService mostPlayedService)
    {
        this.mostPlayedService = mostPlayedService;
    }

    @GetMapping("/mostPlayed")
    public String getMostPlayedTracks(Model model)
    {
        model.addAttribute("appName", appName);
        model.addAttribute("mostPlayed", mostPlayedService.getMostPlayedTracks());

        return "mostPlayed";
    }
}
