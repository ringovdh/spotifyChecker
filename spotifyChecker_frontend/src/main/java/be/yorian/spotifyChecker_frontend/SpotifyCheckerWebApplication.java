package be.yorian.spotifyChecker_frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"be.yorian"})
public class SpotifyCheckerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotifyCheckerWebApplication.class, args);
    }

}
