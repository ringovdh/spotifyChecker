package be.yorian.spotifychecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:spotify.properties")
public class SpotifyCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotifyCheckerApplication.class, args);
    }

}
