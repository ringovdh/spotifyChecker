package be.yorian.spotifyChecker_business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value= "classpath:spotify.properties")
public class SpotifyCheckerBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotifyCheckerBusinessApplication.class, args);
    }

}
