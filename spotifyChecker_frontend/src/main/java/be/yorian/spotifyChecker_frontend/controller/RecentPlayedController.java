package be.yorian.spotifyChecker_frontend.controller;

import be.yorian.spotifyChecker_frontend.service.RecentPlayedService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RecentPlayedController {

    @Value( "${application.name}" )
    private String appName;
    private final RecentPlayedService recentPlayedService;

    public RecentPlayedController(RecentPlayedService recentPlayedService)
    {
        this.recentPlayedService = recentPlayedService;
    }

    @GetMapping("/recentPlayed")
    public String getRecentPlayedTracks(Model model, final HttpSession session)
    {
        String token = (String) session.getAttribute("accessToken");
        String userName = (String) session.getAttribute("userName");
        model.addAttribute("appName", appName);
        model.addAttribute("accessToken", token);
        model.addAttribute("userName", userName);
        model.addAttribute("recentPlayed", recentPlayedService.getRecentPlayedTracks(token));

        return "recentPlayed";
    }
}
